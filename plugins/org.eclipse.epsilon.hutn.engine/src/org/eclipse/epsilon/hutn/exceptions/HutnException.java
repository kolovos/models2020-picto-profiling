/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.hutn.exceptions;

public abstract class HutnException extends Exception {
	
	// Generated by Eclipse
	private static final long serialVersionUID = 4156083316857541986L;

	public HutnException(String message) {
		super(message);
	}
	
	public HutnException(Throwable cause) {
		super(cause);
	}
	
	public HutnException(String message, Throwable cause) {
		super(message, cause);
	}
}
