/*******************************************************************************
 * Copyright (c) 2009 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.flock.dt.launching;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.epsilon.flock.dt.launching.tabs.FlockModelsConfigurationTab;
import org.eclipse.epsilon.flock.dt.launching.tabs.FlockSourceConfigurationTab;

public class FlockLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {

	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) { 
		ILaunchConfigurationTab[] tabs = 
			new ILaunchConfigurationTab[]{
				new FlockSourceConfigurationTab(),
				new FlockModelsConfigurationTab(),
				new CommonTab()
			};
		setTabs(tabs);
	}

}
