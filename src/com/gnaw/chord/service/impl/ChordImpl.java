package com.gnaw.chord.service.impl;

import java.io.Serializable;
import java.net.URL;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gnaw.chord.service.Chord;
import com.gnaw.chord.service.Key;

/**
 * Implementation of the Chord protocol.
 * 
 * @author Josh Tan
 *
 */
public class ChordImpl implements Chord {
	
	private Logger logger;
	
	/**
	 * Default constructor.
	 */
	public ChordImpl() {
		
		// initialize logger
		logger = LogManager.getLogger(ChordImpl.class.getName());
		
		// <test>
		logger.entry();
		logger.trace("Initializing stuff...");
		logger.error("You forgot to implement me!");
		logger.exit();
		// </test>
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create(URL localUrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void join(URL boostrapUrl) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Key key, Serializable object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(Key key, Serializable object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Serializable> retrieve(Key key) {
		// TODO Auto-generated method stub
		return null;
	}

}
