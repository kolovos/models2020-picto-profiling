/*******************************************************************************
 * Copyright (c) 2011 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.egl.formatter.simple;

import org.eclipse.epsilon.egl.formatter.Formatter;

public class UppercaseFormatter implements Formatter {

	@Override
	public String format(String text) {
		return text.toUpperCase();
	}
}
