/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.eugenia;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.models.IModel;

public class FixGenModelDelegate extends EugeniaActionDelegate {
	
	@Override
	public String getBuiltinTransformation() {
		return "transformations/FixGenModel.eol";
	}

	@Override
	public String getCustomizationTransformation() {
		return "FixGenModel.eol";
	}
	
	@Override
	public EugeniaActionDelegateStep getStep() {
		return EugeniaActionDelegateStep.genmodel;
	}
	
	@Override
	public List<IModel> getModels() throws Exception {
		List<IModel> models = new ArrayList<>();

		// The GenModel must be loaded *and* disposed first (bug #307755)
		models.add(loadModel("GenModel", gmfFileSet.getGenModelPath(), GenModelPackage.eINSTANCE.getNsURI(), true, true, false));

		models.add(loadModel("ECore", gmfFileSet.getEcorePath(), EcorePackage.eINSTANCE.getNsURI(), true, false, true));
		return models;
	}

	@Override
	public List<Variable> getExtraVariables() {
		ArrayList<Variable> variables = new ArrayList<>();
		variables.add(CopyrightProvider.getCopyrightVariable(getSelectedFile()));
		return variables;
	}
	
	@Override
	public String getTitle() {
		return "Synchronizing .genmodel model";
	}
}
 
