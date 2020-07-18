/*********************************************************************
 * Copyright (c) 2008 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.picto.source;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.epsilon.common.dt.extensions.ExtensionPointManager;

public class PictoSourceExtensionPointManager extends ExtensionPointManager<PictoSource>{
	
	@Override
	protected PictoSource parse(IConfigurationElement element) throws Exception {
		return (PictoSource) element.createExecutableExtension("class");
	}

	@Override
	protected String getExtensionPointId() {
		return "org.eclipse.epsilon.picto.pictoSource";
	}

}
