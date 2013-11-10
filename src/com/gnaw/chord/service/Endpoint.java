package com.gnaw.chord.service;

/**
 * Wrapper for a Chord node.
 * 
 * @author Josh Tan
 *
 */
public abstract class Endpoint {
	
	private EndpointState currentState;
	
	/**
	 * Notify this endpoint that it can to accept entry requests.
	 */
	public abstract void acceptEntryRequests();
	
	/**
	 * Disconnect from the network.
	 */
	public abstract void disconnect();
	
	
	/**
	 * Register a new listener for state changes of this endpoint.
	 * @param listener 	the listener to register
	 */
	public void register(EndpointStateListener listener) {
		// TODO
	}
	
	/**
	 * Unregister an existing state change listener.
	 * @param listener 	the listener to unregister
	 */
	public void unregister(EndpointStateListener listener) {
		// TODO
	}
	
	/**
	 * Notify this endpoint that it can listen for node connections.
	 */
	public void listen() {
		// TODO
	}
	
	/**
	 * Notify other nodes of a state change for this node.
	 */
	public void notifyNodes() {
		// TODO
	}
	
	/**
	 * Get the current state of this endpoint.
	 * @return	the current state
	 */
	public EndpointState getState() {
		return currentState;
	}
	
	/**
	 * Set the current state for this endpoint.
	 * @param newState	the new state to set
	 */
	public void setState(EndpointState newState) {
		currentState = newState;
	}
	
	/**
	 * Current state of an endpoint.
	 * 
	 * 	STARTED: 			Endpoint has been initialized
	 * 	LISTENING: 			Endpoint is listening for messages for updating its finger
	 * 						table or for status information.
	 * 	ACCEPTING_ENTRIES: 	Endpoint is accepting messages for storing/removing entries.
	 * 
	 * @author Josh Tan
	 *
	 */
	public enum EndpointState {
		STARTED,
		LISTENING,
		ACCEPTING_ENTRIES
	}

}
