package com.gnaw.chord.service;

import java.io.Serializable;

/**
 * An entry in the Chord network.
 * 
 * @author Josh Tan
 *
 */
public class Entry {

	private ChordID id;
	private Serializable value;

	/**
	 * Default constructor.
	 * @param id	ID of the entry
	 * @param value	value for the entry
	 */
	public Entry(ChordID id, Serializable value) {
		this.id = id;
		this.value = value;
	}
	
	/**
	 * Get the ID of the entry.
	 * @return	the entry ID
	 */
	public ChordID getID() {
		return id;
	}
	
	/**
	 * Set the ID for the entry.
	 * @param id	the ID to set
	 */
	public void setID(ChordID id) {
		this.id = id;
	}
	
	/**
	 * Get the value of the entry.
	 * @return	the value of the entry
	 */
	public Serializable getValue() {
		return value;
	}
	
	/**
	 * Set the value for the entry.
	 * @param value	the value to to set
	 */
	public void setValue(Serializable value) {
		this.value = value;
	}
	
}
