/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.eol.dom;

import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.compile.context.EolCompilationContext;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.types.EolPrimitiveType;

public class IntegerLiteral extends LiteralExpression {
	
	protected Number value;
	
	public IntegerLiteral() {}
	
	public IntegerLiteral(Number value) {
		setValue(value);
	}
	
	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		String text = cst.getText();

		/*
		 * int range is -2,147,483,648 to 2,147,483,647, but the INT token in
		 * EolLexerRules.g does not include the leading '-', so anything over 10
		 * characters or anything that the user has explicitly specified as such is
		 * clearly a long value.
		 */
		if (text.endsWith("l") || text.length() > 10) {
			text = cst.getText().substring(0, text.length() - 1);
			value = Long.parseLong(text);
		}
		else {
			/*
			 * If it's not clearly a long value, parse as an int (to avoid additional
			 * overheads from new comparisons), or fall back to parsing it as a long if
			 * needed.
			 */
			try {
				value = Integer.parseInt(text);
			} catch (NumberFormatException ex) {
				value = Long.parseLong(text);
			}
		}
	}
	
	@Override
	public Number execute(IEolContext context) throws EolRuntimeException {
		return getValue();
	}
	
	public Number getValue() {
		return value;
	}
	
	public void setValue(Number value) {
		//TODO: Throw exception if value is not an integer or a long
		this.value = value;
	}
	
	@Override
	public void compile(EolCompilationContext context) {
		resolvedType = EolPrimitiveType.Integer;
	}
	
}
