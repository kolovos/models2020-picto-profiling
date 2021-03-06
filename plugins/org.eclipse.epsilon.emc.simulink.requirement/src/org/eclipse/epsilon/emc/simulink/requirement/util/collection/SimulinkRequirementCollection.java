/*********************************************************************
* Copyright (c) 2008 The University of York.
*
* This program and the accompanying materials are made
* available under the terms of the Eclipse Public License 2.0
* which is available at https://www.eclipse.org/legal/epl-2.0/
*
* SPDX-License-Identifier: EPL-2.0
**********************************************************************/
package org.eclipse.epsilon.emc.simulink.requirement.util.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.epsilon.emc.simulink.model.element.ISimulinkModelElement;
import org.eclipse.epsilon.emc.simulink.requirement.model.SimulinkRequirementModel;
import org.eclipse.epsilon.emc.simulink.requirement.model.element.SimulinkRequirement;
import org.eclipse.epsilon.emc.simulink.requirement.util.manager.SimulinkRequirementManager;
import org.eclipse.epsilon.emc.simulink.types.HandleObject;
import org.eclipse.epsilon.emc.simulink.util.collection.AbstractElementIterator;
import org.eclipse.epsilon.emc.simulink.util.collection.AbstractListIterator;
import org.eclipse.epsilon.emc.simulink.util.collection.AbstractSimulinkCollection;

public class SimulinkRequirementCollection extends AbstractSimulinkCollection<SimulinkRequirement, HandleObject, SimulinkRequirementManager> {

	public SimulinkRequirementCollection(Object primitive, SimulinkRequirementModel model) {
		super(primitive, new SimulinkRequirementManager(model));
	}

	@Override
	protected boolean isInstanceOf(Object object) {
		return object instanceof SimulinkRequirement;
	}

	@Override
	protected boolean isInstanceOfPrimitive(Object object) {
		return HandleObject.is(object) || object.getClass().getName().equals(HandleObject.class.getName());
	}

	@Override
	protected boolean isInstanceOfPrimitiveArray(Object object) {
		if (object instanceof Object[]) {
			return (Arrays.asList(object)).stream().allMatch(h -> HandleObject.is(h));

		}
		return false;
	}
	
	@Override
	public List<ISimulinkModelElement> subList(int fromIndex, int toIndex) {
		return new SimulinkRequirementCollection(getPrimitive().subList(fromIndex, toIndex), getManager().getModel());
	}
	
	@Override
	public ListIterator<ISimulinkModelElement> listIterator() {
		return new SimulinkRequirementListIterator();
	}

	@Override
	public ListIterator<ISimulinkModelElement> listIterator(int index) {
		return new SimulinkRequirementListIterator(index);
	}

	@Override
	protected Iterator<ISimulinkModelElement> getInternalIterator() {
		return new SimulinkRequirementIterator();
	}
		
	protected class SimulinkRequirementIterator extends AbstractElementIterator<SimulinkRequirement, HandleObject, SimulinkRequirementManager>{
		
		SimulinkRequirementIterator(){
			super(getPrimitive(), getManager());
		}
		
	}
	
	protected class SimulinkRequirementListIterator extends AbstractListIterator<SimulinkRequirement, HandleObject, SimulinkRequirementManager> {
		
		SimulinkRequirementListIterator(){
			super(getPrimitive(), getManager());
		}
		
		SimulinkRequirementListIterator(int index){
			super(index, getPrimitive(), getManager());
		}
		
	}


}
