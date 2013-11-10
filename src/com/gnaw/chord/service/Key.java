package com.gnaw.chord.service;

/**
 * Key for an object in the Chord network.
 * 
 * @author Josh Tan
 *
 */
public interface Key {
	
	/**
	 * Return the byte representation of the key.
	 * @return 	byte representation of the key
	 */
	byte[] getBytes();

}
