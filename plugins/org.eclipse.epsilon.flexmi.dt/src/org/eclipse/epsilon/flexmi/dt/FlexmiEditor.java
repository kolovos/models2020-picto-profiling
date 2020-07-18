/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.flexmi.dt;

import java.io.ByteArrayInputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.epsilon.common.dt.util.ThemeChangeListener;
import org.eclipse.epsilon.flexmi.FlexmiResource;
import org.eclipse.epsilon.flexmi.FlexmiResourceFactory;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.ISourceViewerExtension2;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.MarkerUtilities;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.xml.sax.SAXParseException;

public class FlexmiEditor extends TextEditor {

	private FlexmiHighlightingManager highlightingManager;
	protected Job parseResourceJob = null;
	protected FlexmiContentOutlinePage outlinePage = null;
	protected FlexmiResource resource = null;
	protected IFile file;
	
	public FlexmiEditor() {
		super();
		setEditorContextMenuId("#TextEditorContext");
	    setRulerContextMenuId("editor.rulerMenu");
		highlightingManager = new FlexmiHighlightingManager();
		highlightingManager.initialiseDefaultColors();
		setSourceViewerConfiguration(new XMLConfiguration(highlightingManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	
	public IFile getFile() {
		return file;
	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		
		outlinePage = new FlexmiContentOutlinePage(this);
		
		final int delay = 1000;
		
		parseResourceJob = new Job("Parsing module") {
			
			protected int status = -1;
			
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				
				if (!isClosed()) {
					int textHashCode = getText().hashCode();
					if (status != textHashCode) {
						parseResource();
						status = textHashCode;
					}
					
					this.schedule(delay);
				}
				
				return Status.OK_STATUS;
			}
		};
		
		parseResourceJob.setSystem(true);
		parseResourceJob.schedule(delay);
		
		PlatformUI.getWorkbench().getThemeManager().addPropertyChangeListener(new ThemeChangeListener() {
			@Override
			public void themeChange() {
				highlightingManager.initialiseDefaultColors();
				refreshText();
			}
		});
		highlightingManager.getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (highlightingManager.isColorPreference(event.getProperty())) {
					refreshText();
				}
			}
		});
	}

	public void refreshText() {
		ISourceViewer viewer= getSourceViewer();
		if (!(viewer instanceof ISourceViewerExtension2))
			return;
		((ISourceViewerExtension2)viewer).unconfigure();
		
		setSourceViewerConfiguration(new XMLConfiguration(highlightingManager));
		viewer.configure(getSourceViewerConfiguration());
	}
	
	@Override
	protected void doSetSelection(ISelection selection) {
		super.doSetSelection(selection);
	}
	
	public void parseResource() {
		
		// Return early if the file is opened in an unexpected editor (e.g. in a Subclipse RemoteFileEditor)
		if (!(getEditorInput() instanceof FileEditorInput)) return;
		
		FileEditorInput fileInputEditor = (FileEditorInput) getEditorInput();
		file = fileInputEditor.getFile();
		
		final IDocument doc = this.getDocumentProvider().getDocument(
				this.getEditorInput());
		
		// Replace tabs with spaces to match
		// column numbers produced by the parser
		String code = doc.get();
		code = code.replaceAll("\t", " ");
		SAXParseException parseException = null;
		
		ResourceSet resourceSet = new ResourceSetImpl();
		
		try {
			resourceSet.getPackageRegistry().put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new FlexmiResourceFactory());
			resource = (FlexmiResource) resourceSet.createResource(URI.createFileURI(file.getLocation().toOSString()));
			resource.load(new ByteArrayInputStream(code.getBytes()), null);
		}
		catch (Exception ex) {
				
				if (ex instanceof RuntimeException) {
					if (ex.getCause() instanceof TransformerException) {
						if (ex.getCause().getCause() instanceof SAXParseException) {
							parseException = (SAXParseException) ex.getCause().getCause();
						}
					}
				}
				else {
					ex.printStackTrace();
				}
		}
		
		final String markerType = "org.eclipse.epsilon.flexmi.dt.problemmarker";
		
		// Update problem markers
		try {
			file.deleteMarkers(markerType, true, IResource.DEPTH_INFINITE);
			
			for (URI uri : resource.getParsedFragmentURIs()) {
				try {
					for (IFile parsedFragmentFile : ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(new java.net.URI(uri.toString()))) {
						parsedFragmentFile.deleteMarkers(markerType, true, IResource.DEPTH_INFINITE);
					}
				}
				catch (Exception ex) {}
			}
			
			if (parseException != null) {
				createMarker(parseException.getMessage(), parseException.getLineNumber(), true, file, markerType);
			}
			else {
				for (Diagnostic warning : resource.getWarnings()) {
					try {
						for (IFile warningFile : ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(new java.net.URI(warning.getLocation()))) {
							createMarker(warning.getMessage(), warning.getLine(), false, warningFile, markerType);
						}
					} catch (URISyntaxException e) {}
				}
				outlinePage.setResourceSet(resourceSet);
			}
			
		} catch (CoreException e1) {
			e1.printStackTrace();
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object getAdapter(Class required) {
		if (IContentOutlinePage.class.equals(required)) {
			return outlinePage;
		}
		return super.getAdapter(required);
	}
	
	protected void createMarker(String message, int lineNumber, boolean error, IFile file, String markerType) throws CoreException {
		Map<String, Object> attr = new HashMap<>();
		attr.put(IMarker.LINE_NUMBER, lineNumber);
		attr.put(IMarker.MESSAGE, message);				
		int markerSeverity = IMarker.SEVERITY_WARNING;
		if (error) markerSeverity = IMarker.SEVERITY_ERROR;
		attr.put(IMarker.SEVERITY, markerSeverity);
		MarkerUtilities.createMarker(file, attr, markerType);
	}
	
	public boolean isClosed() {
		return this.getDocumentProvider() == null;
	}
	
	public String getText() {
		return this.getDocumentProvider().getDocument(
				this.getEditorInput()).get();
	}
	
	public FlexmiResource getResource() {
		return resource;
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}

}
