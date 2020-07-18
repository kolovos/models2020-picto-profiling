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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.DecoratingColumLabelProvider;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.epsilon.flexmi.EObjectLocation;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.AbstractTextEditor;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySource;

public class FlexmiContentOutlinePage extends ContentOutlinePage {
	
	protected ComposedAdapterFactory adapterFactory;
	protected FlexmiEditor editor;
	
	public FlexmiContentOutlinePage(FlexmiEditor editor) {
		this.editor = editor;
	}
	
	public void setResourceSet(final ResourceSet resourceSet) {
		if (getSite() != null) {
			getSite().getShell().getDisplay().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					if (getTreeViewer() != null) {
						getTreeViewer().setInput(resourceSet);
					}
				}
			});
		}
		
	}
	
	boolean externalSelectionChange = false;
	
    @Override
    public void createControl(Composite parent)
    {
      super.createControl(parent);
      
      adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

      adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
      adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
      adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

      final TreeViewer contentOutlineViewer = getTreeViewer();
      contentOutlineViewer.addSelectionChangedListener(this);

      // Set up the tree viewer.
      contentOutlineViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
      contentOutlineViewer.setLabelProvider(
    		  new DecoratingColumLabelProvider(
    				  new AdapterFactoryLabelProvider(adapterFactory) {   
    					@Override
    					public String getText(Object object) {
    						String text = super.getText(object);
    						if (object instanceof EObject && ((EObject) object).eContainingFeature() != null) {
    							text += " (" + ((EObject) object).eContainingFeature().getName() + ")";
    						}
    						return text;
    					}
    				  }, new DiagnosticDecorator(new ResourceSetImpl(), contentOutlineViewer)));
      contentOutlineViewer.setInput(new ResourceSetImpl());
      getSite().getWorkbenchWindow().getSelectionService().addPostSelectionListener(new ISelectionListener() {
		  
		@Override
		public void selectionChanged(IWorkbenchPart part, ISelection selection) {
			try {
				if (selection instanceof TextSelection) {
					TextSelection textSelection = (TextSelection) selection;
					EObject eObject = editor.getResource().getEObjectTraceManager().getEObject(URI.createFileURI(editor.getFile().getLocation().toOSString()), textSelection.getStartLine()+1);
					
					if (eObject != null) {
						externalSelectionChange = true;
						getTreeViewer().setSelection(new StructuredSelection(eObject), true);
						externalSelectionChange = false;
					}
				}
			}
			catch (Exception ex) {}
		}
      });
      
      
      
      contentOutlineViewer.addSelectionChangedListener(new ISelectionChangedListener() {
		
		@Override
		public void selectionChanged(SelectionChangedEvent event) {
			try {
				final Object selected = ((IStructuredSelection)contentOutlineViewer.getSelection()).getFirstElement();
				
				IAdaptable adaptable = new IAdaptable() {
	
					@Override
					@SuppressWarnings({ "rawtypes", "unchecked" })
					public Object getAdapter(Class adapter) {
						
						if (selected instanceof EObject && adapter == IPropertySource.class) {
							
							return new PropertySource(selected, new ReflectiveItemProvider(new ReflectiveItemProviderAdapterFactory()));
						}
						return null;
					}
					
				};
				
				fireSelectionChanged(new StructuredSelection(adaptable));
				if (externalSelectionChange) return;
				
				IFile file = editor.getFile();
				if (file == null) return;
				
				final EObjectLocation location = editor.getResource().getEObjectTraceManager().getLine((EObject) selected);
				if (!file.getLocation().toOSString().equals(location.getUri().toFileString())) return;
				
				final int line = location.getLine();
					
				
				
				final FileEditorInput fileinput=new FileEditorInput(file);
				final IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
				
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
					@Override
					public void run() {
						try {
							int realLine = line;
							if (realLine == 0) realLine = 1;
							
							AbstractTextEditor editor = (AbstractTextEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(fileinput,desc.getId(), false);		
							IDocument doc = editor.getDocumentProvider().getDocument(fileinput);
							editor.selectAndReveal(doc.getLineOffset(realLine - 1), 0);
						}
						catch (Exception ex) {
							ex.printStackTrace();
						}				
					}
				});
				
			}
			catch (Exception ex) {
				// Ignore
			}
		}
      });
      
    }
    
}