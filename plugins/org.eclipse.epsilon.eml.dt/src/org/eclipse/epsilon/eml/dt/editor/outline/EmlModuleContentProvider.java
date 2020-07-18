/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.eml.dt.editor.outline;

import java.util.List;

import org.eclipse.epsilon.common.module.ModuleElement;
import org.eclipse.epsilon.eml.EmlModule;
import org.eclipse.epsilon.etl.dt.editor.outline.EtlModuleContentProvider;

public class EmlModuleContentProvider extends EtlModuleContentProvider {
	
	@Override
	public List<ModuleElement> getVisibleChildren(ModuleElement moduleElement) {
		List<ModuleElement> visible = super.getVisibleChildren(moduleElement);
		
		if (moduleElement.getClass() == EmlModule.class) {
			EmlModule module = (EmlModule) moduleElement;
			visible.addAll(module.getImports());
			visible.addAll(module.getDeclaredModelDeclarations());
			visible.addAll(module.getDeclaredPre());
			visible.addAll(module.getDeclaredTransformationRules());
			visible.addAll(module.getDeclaredMergeRules());
			visible.addAll(module.getDeclaredPost());
			visible.addAll(module.getDeclaredOperations());
		}
		
		return visible;
	}
	
}
