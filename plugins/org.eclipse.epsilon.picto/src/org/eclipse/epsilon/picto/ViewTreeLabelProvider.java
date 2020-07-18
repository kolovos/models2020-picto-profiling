/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.picto;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.eclipse.epsilon.common.util.UriUtil;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class ViewTreeLabelProvider extends LabelProvider {
	
	protected Image diagramImage = PictoPlugin.getDefault().getImageDescriptor("icons/diagram.gif").createImage();
	protected Image folderImage = PictoPlugin.getDefault().getImageDescriptor("icons/folder.gif").createImage();
	protected HashMap<String, Image> iconCache = new HashMap<>();
	protected List<String> extensions = Arrays.asList("gif", "png");
	
	@Override
	public String getText(Object element) {
		ViewTree contentTree = (ViewTree) element;
		if (contentTree.getName() == null || contentTree.getName().trim().length() == 0) {
			return "<empty>";
		}
		else return contentTree.getName();
	}
	
	@Override
	public Image getImage(Object element) {
		ViewTree contentTree = (ViewTree) element;
		String iconName = contentTree.getIcon();
		Image icon = iconCache.get(iconName);
		
		List<URI> baseUris = new ArrayList<>(contentTree.getBaseUris());
		try {
			baseUris.add(new URI("platform:/plugin/org.eclipse.epsilon.picto/icons/"));
		} catch (URISyntaxException e) {}
		
		if (icon == null) {
			
			if (iconName.matches("diagram-.*")) {
				icon = getColoredDiagramIcon(iconName.replace("diagram-", ""));
				iconCache.put(iconName, icon);
			}
			else {
				outerloop:
				for (URI baseUri : baseUris) {
					for (String extension : extensions) {
						try {
							URI iconUri = UriUtil.resolve(iconName + "." + extension, baseUri);
							icon = iconCache.get(iconUri.toString());
							if (icon == null) {
								icon = new Image(Display.getCurrent(), iconUri.toURL().openStream());
								iconCache.put(iconUri.toString(), icon);
							}
							break outerloop;
						}
						catch (Exception ex) {}
					}
				}
			}
		}
		
		if (icon != null) return icon;
		else return diagramImage;
	}
	
	protected Image getColoredDiagramIcon(String color) {
		Image icon = PictoPlugin.getDefault().getImageDescriptor("icons/diagram.gif").createImage();
		ImageData imageData = icon.getImageData();
		int hex = Integer.decode("0x" + color);
		for (int i=2;i<7;i++) {
			imageData.setPixel(i, 2, hex);
		}
		for (int i=10;i<15;i++) {
			imageData.setPixel(i, 8, hex);
		}
		return new Image(Display.getCurrent(), imageData);
	}
	
	public void clearIconCache() {
		iconCache.clear();
	}
}
