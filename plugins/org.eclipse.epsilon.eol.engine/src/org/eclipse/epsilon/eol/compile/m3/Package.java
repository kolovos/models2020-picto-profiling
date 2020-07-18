/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.eol.compile.m3;

import java.util.ArrayList;
import java.util.List;

public class Package extends NamedElement {
	
	protected List<Package> subPackages = new ArrayList<>();
	protected List<MetaType> metaTypes = new ArrayList<>();
	
	public List<Package> getSubPackages() {
		return subPackages;
	}
	
	public List<MetaType> getTypes() {
		return metaTypes;
	}
	
}
