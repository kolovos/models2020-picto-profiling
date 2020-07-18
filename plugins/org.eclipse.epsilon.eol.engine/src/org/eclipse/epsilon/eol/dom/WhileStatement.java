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
import org.eclipse.epsilon.common.module.ModuleMarker;
import org.eclipse.epsilon.common.module.ModuleMarker.Severity;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.compile.context.EolCompilationContext;
import org.eclipse.epsilon.eol.exceptions.EolIllegalReturnException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.flowcontrol.EolBreakException;
import org.eclipse.epsilon.eol.exceptions.flowcontrol.EolContinueException;
import org.eclipse.epsilon.eol.execute.ExecutorFactory;
import org.eclipse.epsilon.eol.execute.Return;
import org.eclipse.epsilon.eol.execute.context.FrameStack;
import org.eclipse.epsilon.eol.execute.context.FrameType;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.types.EolPrimitiveType;

public class WhileStatement extends Statement {
	
	protected Expression conditionExpression;
	protected StatementBlock bodyStatementBlock;
	
	public WhileStatement() {}
	
	public WhileStatement(Expression conditionExpression, StatementBlock bodyStatementBlock) {
		this.conditionExpression = conditionExpression;
		this.bodyStatementBlock = bodyStatementBlock;
	}

	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		conditionExpression =  (Expression) module.createAst(cst.getFirstChild(), this);
		bodyStatementBlock = toStatementBlock(module.createAst(cst.getSecondChild(), this));
	} 

	@Override
	public Object execute(IEolContext context) throws EolRuntimeException {
		FrameStack frameStack = context.getFrameStack();
		ExecutorFactory executorFactory = context.getExecutorFactory();
		
		for (int loop = 1; ; ++loop) {
			frameStack.enterLocal(FrameType.UNPROTECTED, this);
			
			Object condition = executorFactory.execute(conditionExpression, context);		
			
			if (!(condition instanceof Boolean)) {
				frameStack.leaveLocal(this);
				throw new EolIllegalReturnException("Boolean", condition, conditionExpression, context);
			}
			
			Object result = null;
			
			if ((boolean) condition) {
				frameStack.put(Variable.createReadOnlyVariable("loopCount", loop));
				
				try {
					result = executorFactory.execute(bodyStatementBlock, context);
				}
				catch (EolBreakException bex) {
					if (bex.isBreaksAll() && frameStack.isInLoop()) {
						throw bex;
					}
					frameStack.leaveLocal(this);
					break;
				}
				catch (EolContinueException cex){
					frameStack.leaveLocal(this);
					continue;
				}

			}
			else {
				frameStack.leaveLocal(this);
				break;
			}
			
			frameStack.leaveLocal(this);
		
			if (result instanceof Return) {
				return result;
			}
		}
		
		return null;
	}
	
	@Override
	public void compile(EolCompilationContext context) {
		FrameStack frameStack = context.getFrameStack();
		conditionExpression.compile(context);
		
		frameStack.enterLocal(FrameType.UNPROTECTED, bodyStatementBlock);
		bodyStatementBlock.compile(context);
		frameStack.leaveLocal(bodyStatementBlock);
		
		if (conditionExpression.hasResolvedType() && conditionExpression.getResolvedType() != EolPrimitiveType.Boolean) {
			context.getMarkers().add(new ModuleMarker(conditionExpression, "Condition must be a Boolean", Severity.Error));
		}		
	}

	public Expression getConditionExpression() {
		return conditionExpression;
	}
	
	public void setConditionExpression(Expression conditionExpression) {
		this.conditionExpression = conditionExpression;
	}
	
	public StatementBlock getBodyStatementBlock() {
		return bodyStatementBlock;
	}
	
	public void setBodyStatementBlock(StatementBlock bodyStatementBlock) {
		this.bodyStatementBlock = bodyStatementBlock;
	}	
}
