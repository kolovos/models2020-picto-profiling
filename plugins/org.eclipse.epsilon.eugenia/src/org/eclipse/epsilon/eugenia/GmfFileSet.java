/*******************************************************************************
 * Copyright (c) 2012 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.eugenia;

import org.eclipse.epsilon.common.util.FileUtil;

public class GmfFileSet {
	
	protected String basePath = "";
	
	public GmfFileSet(String onePath) {
		basePath = FileUtil.replaceExtension(onePath, "");
	}
	
	public String getEcorePath() {
		return basePath + "ecore";
	}
	
	public String getEmfaticPath() {
		return basePath + "emf";
	}
	
	public String getGenModelPath() {
		return basePath + "genmodel";
	}
	
	public String getGmfToolPath() {
		return basePath + "gmftool";
	}
	
	public String getGmfMapPath() {
		return basePath + "gmfmap";
	}
	
	public String getGmfGenPath() {
		return basePath + "gmfgen";
	}
	
	public String getGmfGraphPath() {
		return basePath + "gmfgraph";
	}
	
}
