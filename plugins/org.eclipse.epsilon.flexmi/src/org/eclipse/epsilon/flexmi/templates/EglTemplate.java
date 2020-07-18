/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.flexmi.templates;

import java.net.URI;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.epsilon.egl.EglTemplateFactory;
import org.eclipse.epsilon.egl.EglTemplateFactoryModuleAdapter;
import org.eclipse.epsilon.emc.plainxml.StringInputStream;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.flexmi.xml.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class EglTemplate extends XmlTemplate {

	public EglTemplate(Element element, URI uri) {
		super(element, uri);
	}

	@Override
	public List<Element> getApplication(Element call) {
		try {
			EglTemplateFactoryModuleAdapter module = new EglTemplateFactoryModuleAdapter(new EglTemplateFactory());
			module.parse(content.getTextContent().trim(), uri);
			
			for (Parameter parameter : getParameters()) {
				String parameterName = parameter.getName();
				String value = call.getAttribute(parameterName);
				if (call.hasAttribute(Template.PREFIX + parameterName)) value = call.getAttribute(Template.PREFIX + parameterName);
				module.getContext().getFrameStack().put(Variable.createReadOnlyVariable(parameterName, value));
			}
			
			String xml = "<?xml version=\"1.0\"?><root>" + (module.execute() + "").trim() + "</root>";
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(new StringInputStream(xml));
			return Xml.getChildren(document.getDocumentElement());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
