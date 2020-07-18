/*******************************************************************************
 * Copyright (c) 2013 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.eugenia.patches;

import java.util.List;

import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eugenia.EugeniaActionDelegate;
import org.eclipse.epsilon.eugenia.EugeniaActionDelegateStep;
import org.eclipse.jface.action.IAction;

public class UnapplyPatchesDelegate extends EugeniaActionDelegate {
	
	@Override
	public String getTitle() {
		return "Removing previously applied patches";
	}

	@Override
	public EugeniaActionDelegateStep getStep() {
		return EugeniaActionDelegateStep.unapplypatches;
	}

	@Override
	public List<IModel> getModels() throws Exception {
		return null;
	}

	@Override
	public String getBuiltinTransformation() {
		return null;
	}

	@Override
	public String getCustomizationTransformation() {
		return null;
	}
	
	@Override
	public void runImpl(IAction action) {
		new Patcher(getSelection().getProject()).runBackwards();
	}
}
