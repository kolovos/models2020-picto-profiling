/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.eugenia.operationcontributors;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.execute.operations.contributors.OperationContributor;

public class EClassOperationContributor extends OperationContributor {

	@Override
	public boolean contributesTo(Object target) {
		return target instanceof EClass;
	}
	
	public EAttribute attr(String name) throws EolRuntimeException {
		EClass target = (EClass) getTarget();
		for (EAttribute attr : target.getEAttributes()) {
			if (name.equals(attr.getName())) return attr;
		}
		throw new EolRuntimeException("Class " + target.getName() + " does not own an attributed named " + name);
	}
	
	public EReference ref(String name) throws EolRuntimeException {
		EClass target = (EClass) getTarget();
		for (EReference ref : target.getEReferences()) {
			if (name.equals(ref.getName()) && !ref.isContainment()) return ref;
		}
		throw new EolRuntimeException("Class " + target.getName() + " does not own an non-containment reference named " + name);
	}
	
	public EReference val(String name) throws EolRuntimeException {
		EClass target = (EClass) getTarget();
		for (EReference ref : target.getEReferences()) {
			if (name.equals(ref.getName()) && ref.isContainment()) return ref;
		}
		throw new EolRuntimeException("Class " + target.getName() + " does not own a containment reference named " + name);
	}
	
}
