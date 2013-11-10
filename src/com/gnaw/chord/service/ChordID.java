package com.gnaw.chord.service;

/**
 * ID for a Chord nodes and object.
 * 
 * @author Josh Tan
 *
 */
public class ChordID {
	
	private byte[] id;
	
	/**
	 * Default constructor.
	 * @param id	ID to initialize to
	 */
	public ChordID(byte[] id) {
		this.id = id; 
	}
	
	/**
	 * Get the length of this ID. The length is the number of
	 * bytes in the array * (8 bits / byte).
	 * @return	the ID length
	 */
	public int getLength() {
		return id.length * 8;
	}

}
