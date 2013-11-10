package com.gnaw.chord.service;

import com.gnaw.chord.service.Endpoint.EndpointState;

/**
 * Interface for a listener of endpoint state changes.
 * 
 * @author Josh Tan
 *
 */
public interface EndpointStateListener {
	
	/**
	 * Notify listener that its endpoint has changed its state.
	 * @param newState	the state changed to
	 */
	void notify(EndpointState newState);

}
