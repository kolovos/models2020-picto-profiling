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
import org.eclipse.epsilon.eol.execute.ExecutorFactory;
import org.eclipse.epsilon.eol.execute.context.FrameStack;
import org.eclipse.epsilon.eol.execute.context.FrameType;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.types.EolPrimitiveType;

public class IfStatement extends Statement {
	
	protected Expression conditionExpression;
	protected StatementBlock thenStatementBlock;
	protected StatementBlock elseStatementBlock;
	
	public IfStatement() {}
	
	public IfStatement(Expression conditionExpression, StatementBlock thenStatementBlock, StatementBlock elseStatementBlock) {
		this.conditionExpression = conditionExpression;
		this.thenStatementBlock = thenStatementBlock;
		this.elseStatementBlock = elseStatementBlock;
	}

	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		conditionExpression = (Expression) module.createAst(cst.getFirstChild(), this);
		thenStatementBlock = toStatementBlock(module.createAst(cst.getSecondChild(), this));
		if (cst.getChildCount() == 3) elseStatementBlock = toStatementBlock(module.createAst(cst.getThirdChild(), this));
	}

	@Override
	public Object execute(IEolContext context) throws EolRuntimeException {
		FrameStack frameStack = context.getFrameStack();
		ExecutorFactory executorFactory = context.getExecutorFactory();
		
		frameStack.enterLocal(FrameType.UNPROTECTED, this);
		Object condition = executorFactory.execute(conditionExpression, context);
		
		if (!(condition instanceof Boolean)) throw new EolIllegalReturnException("Boolean", condition, conditionExpression, context);
		
		Object result = null;
		
		if ((boolean) condition) {
			result = executorFactory.execute(thenStatementBlock, context);
		}
		else if (elseStatementBlock != null) {
			result = executorFactory.execute(elseStatementBlock, context);
		}
		
		frameStack.leaveLocal(this);
		return result;
	}
	
	@Override
	public void compile(EolCompilationContext context) {
		conditionExpression.compile(context);
		FrameStack frameStack = context.getFrameStack();
		
		frameStack.enterLocal(FrameType.UNPROTECTED, thenStatementBlock);
		thenStatementBlock.compile(context);
		frameStack.leaveLocal(thenStatementBlock);
		
		if (elseStatementBlock != null) {
			frameStack.enterLocal(FrameType.UNPROTECTED, elseStatementBlock);
			elseStatementBlock.compile(context);
			context.getFrameStack().leaveLocal(elseStatementBlock);
		}
		
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
	
	public StatementBlock getThenStatementBlock() {
		return thenStatementBlock;
	}
	
	public void setThenStatementBlock(StatementBlock thenStatementBlock) {
		this.thenStatementBlock = thenStatementBlock;
	}
	
	public StatementBlock getElseStatementBlock() {
		return elseStatementBlock;
	}
	
	public void setElseStatementBlock(StatementBlock elseStatementBlock) {
		this.elseStatementBlock = elseStatementBlock;
	}
}
