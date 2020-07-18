/*******************************************************************************
 * Copyright (c) 2011 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.egl.spec;

import java.net.URI;
import java.util.Collection;
import java.util.LinkedList;
import org.eclipse.epsilon.egl.execute.control.ITemplateExecutionListener;
import org.eclipse.epsilon.egl.formatter.Formatter;
import org.eclipse.epsilon.egl.incremental.IncrementalitySettings;
import org.eclipse.epsilon.egl.internal.IEglModule;
import org.eclipse.epsilon.egl.traceability.Template;

public abstract class EglTemplateSpecification {

	private final String name;
	private final Formatter defaultFormatter;
	private final IncrementalitySettings incrementalitySettings;
	private final Collection<ITemplateExecutionListener> listeners;
	
	protected EglTemplateSpecification(String name, Formatter defaultFormatter, IncrementalitySettings incrementalitySettings, Collection<ITemplateExecutionListener> listeners) {
		this.name = name;
		this.defaultFormatter = defaultFormatter;
		this.incrementalitySettings = incrementalitySettings;
		this.listeners = listeners;
	}
	
	public String getName() {
		return name;
	}
	
	public Formatter getDefaultFormatter() {
		return defaultFormatter;
	}
	
	public IncrementalitySettings getIncrementalitySettings() {
		// Return a defensive copy, which can be safely modified for only this template
		return new IncrementalitySettings(incrementalitySettings);
	}
	
	public Collection<ITemplateExecutionListener> getTemplateExecutionListeners() {
		// Return a defensive copy, which can be safely modified for only this template
		return new LinkedList<>(listeners);
	}

	public abstract Template createTemplate();
	public abstract void parseInto(IEglModule module) throws Exception;
	public abstract URI getURI();
}
