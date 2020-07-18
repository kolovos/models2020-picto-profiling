/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.egl.preprocessor;

import static org.eclipse.epsilon.egl.util.FileUtil.NEWLINE;
import static org.eclipse.epsilon.egl.util.StringUtil.isWhitespace;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import org.antlr.runtime.Token;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.common.parse.Region;
import org.eclipse.epsilon.egl.parse.EglToken.TokenType;
import org.eclipse.epsilon.egl.util.FileUtil;
import org.eclipse.epsilon.eol.parse.EolParser;

public class Preprocessor {
	
	private StringBuffer eol = new StringBuffer();
	private final Map<Integer, Integer> colNumber = new TreeMap<>();
	private PreprocessorTrace trace = new PreprocessorTrace();
	private AST child = null;
	
	
	private static String escape(String s) {
		return s
			.replaceAll("\\\\","\\\\\\\\")
			.replaceAll("\r","\\\\r")
			.replaceAll("\n","\\\\n")
			.replaceAll("\t","\\\\t")
			.replaceAll("\b","\\\\b")
			.replaceAll("\f","\\\\f")
			.replace("'", "\\'")
			.replace("\"", "\\\"");
	}
	
	private int getOffset(int lineNumber) {
		if (colNumber.containsKey(lineNumber))
			return colNumber.get(lineNumber);
		
		return 0;
	}
	
	private void addToOffset(int lineNumber, int addend) {
		colNumber.put(lineNumber, getOffset(lineNumber) + addend);
	}

	private void updateOffset(int eglLineNumber, int correction, int textLength) {	
		// Update trace
		trace.incrementColumnCorrectionNumber(getOffset(eglLineNumber) + correction);
		
		// Store new column number as current position + text length + length of %]
		addToOffset(eglLineNumber, correction + textLength + 2);
	}

	private void appendNewLineToEol(int eglLineNumber) {
		appendNewLineToEol(eglLineNumber, eol.length() > 0);
	}
	
	private void appendNewLineToEol(int eglLineNumber, boolean appendNewLine) {
		if (appendNewLine) eol.append(NEWLINE);
		trace.setEglLineNumberForCurrentEolLineNumber(eglLineNumber);
	}

	private void appendToEolOnANewLine(String text, int eglLineNumber) {
		appendNewLineToEol(eglLineNumber);
		eol.append(text);
	}
	
	private boolean eolEndsWith(String suffix) {
		if (suffix.length() > eol.length()) return false;
		
		return eol.substring(eol.length() - suffix.length(), eol.length()).equals(suffix);
	}
	
	public String convertToEol(AST ast) {
		
		// Reset vars
		eol = new StringBuffer();
		colNumber.clear();
		trace.reset();
		
		child = ast.getFirstChild();
		
		while (child != null) {
			switch (TokenType.typeOf(child.getType())) {
				case START_COMMENT_TAG:
					int commentLength = child.getText().length();
					
					AST current = child.getFirstChild();
					while (current != null) {
						commentLength += current.getText().length();
						current = current.getNextSibling();
					}
					
					addToOffset(child.getLine(), commentLength);
					
					// Gobble new lines after [* *] pairs
					gobbleNextIfNewLine();
					
					break;
					
				case NEW_LINE:
				case PLAIN_TEXT:
									
					boolean isWhitespacePrecedingTagged = TokenType.typeOf(child.getType()) != TokenType.NEW_LINE &&
					                                      isWhitespace(child.getText())  &&
					                                      child.getNextSibling() != null &&
					                                      (TokenType.typeOf(child.getNextSibling().getType()) == TokenType.START_TAG ||
					                                       TokenType.typeOf(child.getNextSibling().getType()) == TokenType.START_COMMENT_TAG);
					
					final String text = normaliseNewLines(child.getText()); 
					
					// Gobble whitespace before [% %] and [* *] pairs
					if (!isWhitespacePrecedingTagged) {
						appendToEolOnANewLine("out.prinx('" + escape(text) + "');", child.getLine());
						
						if (TokenType.typeOf(child.getType()) == TokenType.PLAIN_TEXT) {
							String printCall = "out.prinx('";
							// Update trace to account for length of printCall
							trace.incrementColumnCorrectionNumber(getOffset(child.getLine()) + -printCall.length());
						}
					}
					
					addToOffset(child.getLine(), text.length());
					
					break;
					
				case START_TAG:
				case START_OUTPUT_TAG:
					
					// Ensure that this section generates a new line of EOL
					if (!eolEndsWith(NEWLINE)) {
						appendNewLineToEol(child.getLine());
					}
					
					if (TokenType.typeOf(child.getType()) == TokenType.START_TAG) {
						// This is a dynamic section
						
						AST textAst = child.getFirstChild();
						boolean firstLine = true;
						
						while (textAst != null && TokenType.typeOf(textAst.getType()) != TokenType.END_TAG) {
							
							if (TokenType.typeOf(textAst.getType()) == TokenType.NEW_LINE) {
								
								if (TokenType.typeOf(textAst.getNextSibling().getType()) != TokenType.END_TAG) {
									appendNewLineToEol(textAst.getLine()+1, true);
									firstLine = false;
								}
							
							} else {
								final int correction = firstLine ? 2 : 0;
								updateOffset(textAst.getLine(), correction, textAst.getText().length());
								
								eol.append(textAst.getText());
							}
							
							textAst = textAst.getNextSibling();
						}
						
						// Gobble new lines after [% %] pairs
						gobbleNextIfNewLine();
					
					} else {
						// This is a dynamic output section 
						
						updateOffset(child.getLine(), "[%=".length(), child.getFirstChild().getText().length());
						
						String printCall = "out.printdyn(";
						
						// Update trace to account for length of printCall
						trace.incrementColumnCorrectionNumber(-printCall.length());
						
						eol.append(printCall + child.getFirstChild().getText() + ");");
					}
					
					break;
					
				default:
					// ignore
					break;
			}
			
			child = child.getNextSibling();
		}
		
		return eol.toString();
	}
	
	/**
	 * Converts any newline characters to the correct newline character for the
	 * environment in which the template is executing, as the template 
	 * might contain newline characters that are not appropriate for the
	 * environment in which it is currently executing.
	 */
	private static String normaliseNewLines(String text) {
		return text
			// First normalise newlines to \n
			.replaceAll("\\r\\n", "\n")
			.replaceAll("\\r", "\n")
			// Now replace with current newline
			.replaceAll("\\n", FileUtil.NEWLINE);
	}


	private void gobbleNextIfNewLine() {
		boolean nextNodeIsNewLine = child.getNextSibling() != null &&
		                            TokenType.typeOf(child.getNextSibling().getType()) == TokenType.NEW_LINE;
		
		if (nextNodeIsNewLine) {
			child = child.getNextSibling(); // skip the next node
		}
	}
	
	public PreprocessorTrace getTrace() {
		return trace;
	}
	
	/**
	 * Updates the EOL ASTs produced by the preprocessor from EGL static sections.
	 * 
	 * In the generated EOL, static sections appear as statements of the form:
	 * out.prinx("the static text")
	 *           12345678901234567
	 *           0        1
	 * 
	 * In the EGL AST outline view, we display (a filtered version of) the preprocessed
	 * EOL's AST, which contains a STRING node. For the above statement, this would be:
	 * 
	 * STRING, 1:1 to 1:17
	 * 
	 * Note that the start (end) column includes the opening (closing) double quote. This 
	 * method corrects this issue by finding all AST nodes that correspond to text
	 * generated for static sections and adjusting the regions of the nested STRING nodes.
	 * 
	 * This method also "hides" (makes imaginary) any AST nodes that need not be displayed
	 * in the AST outline view.
	 * 
	 * @return true if and only iff this method has processed all nested AST nodes and
	 *         this part of the AST should not be processed any further.
	 */
	protected boolean updateRegionsOfStaticTextASTs(AST ast) {
		// Turn out.prinx("something") / out.printdyn(x) to imaginary and fix the region of the parameter
		if (ast.getType() == EolParser.POINT && ast.getNumberOfChildren() == 2) {
			AST outAst = ast.getFirstChild();
			AST printAst = ast.getChild(1);
			
			if ("out".equals(outAst.getText()) && ("prinx".equals(printAst.getText()) || "printdyn".equals(printAst.getText()))) {
				AST parametersAst = printAst.getFirstChild();
				if (parametersAst != null) {
					AST firstParameterAst = parametersAst.getFirstChild();
					if (firstParameterAst != null) {
						ast.setImaginary(true);
						outAst.setImaginary(true);
						printAst.setImaginary(true);
						parametersAst.setImaginary(true);
						
						updateASTLocations(firstParameterAst);
						Region region = firstParameterAst.getRegion();
						
						Region adjustedRegion = null;
						
						// For out.prinx("something") we need to lose the double quotes
						if ("prinx".equals(printAst.getText()) && firstParameterAst.getType() == EolParser.STRING) {
							adjustedRegion = new Region(region.getStart().getLine(), region.getStart().getColumn()+1,
								region.getEnd().getLine(), region.getEnd().getColumn() - 1);
							firstParameterAst.setRegion(adjustedRegion);
						}
						else {
							adjustedRegion = region;
						}
						
						// Turn out.print("\n") and out.print("\r\n") to imaginary
						if ("\\n".equals(firstParameterAst.getText()) || "\\r\\n".equals(firstParameterAst.getText())) firstParameterAst.setImaginary(true);
						
						// Make all involved ASTs imaginary and assign them the region of the first parameter
						for (AST imaginary : Arrays.asList(ast, outAst, printAst, parametersAst)) {
							imaginary.setColumn(getTrace().getEglColumnNumberFor(imaginary.getLine(), imaginary.getColumn()));
							imaginary.setLine(getTrace().getEglLineNumberFor(imaginary.getLine()));
							imaginary.setImaginary(true);
							imaginary.setRegion(adjustedRegion);
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void updateASTLocations(AST ast) {
		ast.setColumn(getTrace().getEglColumnNumberFor(ast.getLine(), ast.getColumn()));
		ast.setLine(getTrace().getEglLineNumberFor(ast.getLine()));
		
		for (Token token : ast.getExtraTokens()) {
			if (token == null) continue;
			token.setCharPositionInLine(getTrace().getEglColumnNumberFor(token.getLine(), token.getCharPositionInLine()));
			token.setLine(getTrace().getEglLineNumberFor(token.getLine()));
		}
		
		boolean done = updateRegionsOfStaticTextASTs(ast);
		
		ast.setRegion(null); // Force ast to recompute its region
		
		if (!done) {
			for (AST child : ast.getChildren()) {
				updateASTLocations(child);
			}
		}
	}
}
