/*******************************************************************************
 * Copyright (c) 2012 The University of York.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.eol.dt.debug;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IBreakpoint;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.IMemoryBlock;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.epsilon.eol.IEolModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

public class EolDebugTarget extends EolDebugElement implements IDebugTarget {

	protected boolean suspended;
	protected boolean terminated;
	protected EolDebugger debugger;
	protected ILaunch launch;
	protected IThread[] threads = new IThread[1];
	protected IProcess process;
	protected IEolModule module;
	protected String name;
	
	public EolDebugTarget(ILaunch launch, IEolModule module, EolDebugger debugger, String name) {
		super(null);
		this.launch = launch;
		threads[0] = new EolThread(this);
		this.module = module;
		this.debugger = debugger;
		module.getContext().getExecutorFactory().setExecutionController(debugger);
		this.name = name;
	}
	
	public IEolModule getModule() {
		return module;
	}
	
	public void stepInto() throws DebugException {
		fireSuspendEvent(DebugEvent.SUSPEND);
		debugger.step();
		fireResumeEvent(DebugEvent.STEP_INTO);
	}

	public void stepOver() throws DebugException {
		fireSuspendEvent(DebugEvent.SUSPEND);
		debugger.stepOver();
		fireResumeEvent(DebugEvent.STEP_OVER);
	}

	public void stepReturn() {
		fireSuspendEvent(DebugEvent.SUSPEND);
		debugger.stepReturn();
		fireResumeEvent(DebugEvent.STEP_RETURN);
	}

	public Object debug() throws DebugException, EolRuntimeException {
		fireCreationEvent();
		return debugger.debug(module);
	}

	@Override
	public EolDebugTarget getDebugTarget() {
		return this;
	}

	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public boolean canTerminate() {
		return !isTerminated();
	}

	@Override
	public boolean isTerminated() {
		return terminated;
	}

	@Override
	public void terminate() throws DebugException {
		this.terminated = true;
		this.suspended = false;
		fireTerminateEvent();
	}

	@Override
	public boolean canResume() {
		return isSuspended() && !isTerminated();
	}

	@Override
	public boolean canSuspend() {
		return !isSuspended() && !isTerminated();
	}

	@Override
	public boolean isSuspended() {
		return suspended;
	}

	@Override
	public void resume() throws DebugException {
		this.suspended = false;
		fireResumeEvent(DebugEvent.RESUME);
	}

	@Override
	public void suspend() throws DebugException {
		this.suspended = true;
		fireSuspendEvent(DebugEvent.SUSPEND);
	}

	@Override
	public void breakpointAdded(IBreakpoint breakpoint) {
		// nothing to do
	}

	@Override
	public void breakpointRemoved(IBreakpoint breakpoint, IMarkerDelta delta) {
		// nothing to do
	}

	@Override
	public void breakpointChanged(IBreakpoint breakpoint, IMarkerDelta delta) {
		// nothing to do
	}

	@Override
	public IProcess getProcess() {
		return null;
	}

	@Override
	public IThread[] getThreads() throws DebugException {
		return threads;
	}

	@Override
	public boolean hasThreads() throws DebugException {
		return true;
	}

	@Override
	public String getName() throws DebugException {
		return name;
	}

	@Override
	public boolean supportsBreakpoint(IBreakpoint breakpoint) {
		return false;
	}

	/*
	 * Irrelevant methods
	 */

	@Override
	public boolean canDisconnect() {
		return false;
	}

	@Override
	public void disconnect() throws DebugException {
		// do nothing
	}

	@Override
	public boolean isDisconnected() {
		return false;
	}

	@Override
	public boolean supportsStorageRetrieval() {
		return false;
	}

	@Override
	public IMemoryBlock getMemoryBlock(long startAddress, long length) throws DebugException {
		return null;
	}

}
