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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.epsilon.common.module.IModule;
import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.compile.context.EolCompilationContext;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.EolTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelNotFoundException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.types.EolAnyType;
import org.eclipse.epsilon.eol.types.EolCollectionType;
import org.eclipse.epsilon.eol.types.EolMapType;
import org.eclipse.epsilon.eol.types.EolModelElementType;
import org.eclipse.epsilon.eol.types.EolNativeType;
import org.eclipse.epsilon.eol.types.EolNoType;
import org.eclipse.epsilon.eol.types.EolPrimitiveType;
import org.eclipse.epsilon.eol.types.EolType;

public class TypeExpression extends Expression {
	
	protected EolType type = EolAnyType.Instance;
	protected String name;
	protected List<TypeExpression> parameterTypeExpressions = new ArrayList<>();
	protected StringLiteral nativeType;
	
	public TypeExpression() {}
	
	public TypeExpression(String typeName) {
		setName(typeName);
	}
	
	@Override
	public void build(AST cst, IModule module) {
		super.build(cst, module);
		
		setName(cst.getText());
		for (AST child : cst.getChildren()) {
			ModuleElement moduleElement = module.createAst(child, this);
			
			if (moduleElement instanceof TypeExpression) {
				parameterTypeExpressions.add((TypeExpression) moduleElement);
			}
			else if ("Native".equals(name)) {
				nativeType = (StringLiteral) moduleElement;	
			}
		}
	}
	
	@Override
	public EolType execute(IEolContext context) throws EolRuntimeException {
		if (type != null) return type;
		
		if ("Native".equals(getName())) {
			return new EolNativeType(nativeType, context);
		}
		
		try {
			return new EolModelElementType(name, context);
		}
		catch (EolModelNotFoundException | EolModelElementTypeNotFoundException ex) {
			throw new EolTypeNotFoundException(getName(), this);
		}
	}
	
	@Override
	public void compile(EolCompilationContext context) {
		
		for (TypeExpression typeExpression : parameterTypeExpressions) {
			typeExpression.compile(context);
		}
		
		if (type instanceof EolCollectionType) {
			if (parameterTypeExpressions.size() == 1) {
				((EolCollectionType) type).setContentType(parameterTypeExpressions.get(0).getCompilationType());
			}
			else if (parameterTypeExpressions.size() > 1) {
				context.addErrorMarker(this, "Collection types can have at most one content type");
			}
		}
		
		if (type instanceof EolMapType) {
			if (parameterTypeExpressions.size() == 2) {
				((EolMapType) type).setKeyType(parameterTypeExpressions.get(0).getCompilationType());
				((EolMapType) type).setValueType(parameterTypeExpressions.get(1).getCompilationType());
			}
			else if (parameterTypeExpressions.size() > 0) {
				context.addErrorMarker(this, "Maps need two types: key-type and value-type");
			}
		}
		
		if (type == null) {
			//TODO: Remove duplication between this and NameExpression
			EolModelElementType modelElementType = context.getModelElementType(name);
			if (modelElementType != null) {
				type = modelElementType;
				if (modelElementType.getMetaClass() == null && !context.getModelDeclarations().isEmpty()) {
					context.addErrorMarker(this, "Unknown type " + name);
				}
			}
			else {
				context.addErrorMarker(this, "Undefined variable or type " + name);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		switch (this.name = name) {
			case "Integer":
				type = EolPrimitiveType.Integer;
				break;
			case "Any":
				type = EolAnyType.Instance;
				break;
			case "Boolean":
				type = EolPrimitiveType.Boolean;
				break;
			case "String":
				type = EolPrimitiveType.String;
				break;
			case "Real":
				type = EolPrimitiveType.Real;
				break;
			case "Map": case "ConcurrentMap":
				type = new EolMapType(name);
				break;
			case "List": name = "Sequence";
			case "Bag": case "Sequence": case "Collection":
			case "Set": case "OrderedSet":
			case "ConcurrentSet": case "ConcurrentBag":
				type = new EolCollectionType(name);
				break;
			case "Nothing":
				type = EolNoType.Instance;
				break;
			default:
				type = null;
				break;
		}
	}
	
	public EolType getCompilationType() {
		return type;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+": "+getName();
	}

	public List<TypeExpression> getParameterTypeExpressions() {
		return parameterTypeExpressions;
	}
	
	public StringLiteral getNativeType() {
		return nativeType;
	}
}
