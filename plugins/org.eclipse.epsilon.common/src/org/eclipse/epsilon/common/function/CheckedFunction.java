/*********************************************************************
 * Copyright (c) 2018 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.common.function;

import java.util.function.Function;

/**
 * 
 * @author Sina Madani
 * @since 1.6
 */
@FunctionalInterface
public interface CheckedFunction<T, R, E extends Exception> extends Function<T, R> {
	
	@Override
	default R apply(T t) throws RuntimeException {
		try {
			return applyThrows(t);
		}
		catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	R applyThrows (T t) throws E;
	
}
