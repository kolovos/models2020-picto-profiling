/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.common.dt.examples;

public class ExampleProjectWizard32 extends WebGitProjectNewWizard {
	
	public ExampleProjectWizard32() {
		super("Reuse EUnit tests with model and data bindings", "In this example we show how the same EUnit test can be reused for several models, and how EUnit supports several levels of parametric tests.", "org.eclipse.epsilon.eunit.examples.bindings",
				"https://git.eclipse.org", "/c/epsilon/org.eclipse.epsilon.git/plain/examples/org.eclipse.epsilon.eunit.examples.bindings/");
		
	}

}