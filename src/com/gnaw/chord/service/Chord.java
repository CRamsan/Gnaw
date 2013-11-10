package com.gnaw.chord.service;

import java.io.Serializable;
import java.net.URL;
import java.util.Set;

/**
 * Interface for a Chord network.
 * 
 * @author Josh Tan
 *
 */
public interface Chord {
	
	/**
	 * Create a new Chord network.
	 */
	void create();
	
	/**
	 * Create a new Chord network, using the given local URL.
	 * 
	 * @param localUrl	the URL on which to accept incoming requests from Chord nodes
	 */
	void create(URL localUrl);
	
	/**
	 * Join an existing Chord network using the given boostrap URL.
	 * @param boostrapUrl 	the URL of the Chord network to bootstrap into
	 */
	void join(URL boostrapUrl);
	
	
	/**
	 * Disconnect the network.
	 */
	void disconnect();
	
	/**
	 * Insert an object with the given key into the network.
	 * @param key		key of the object
	 * @param object	the object to insert
	 */
	void insert(Key key, Serializable object);
	
	/**
	 * Remove object with given key from the network.
	 * @param key
	 * @param object
	 */
	void remove(Key key, Serializable object);
	
	/**
	 * Retrieve all objects with the given key.
	 * @param key	the key of objects to remove
	 * @return		objects with the given key
	 */
	Set<Serializable> retrieve(Key key); 
	
}
