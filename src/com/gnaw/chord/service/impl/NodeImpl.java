package com.gnaw.chord.service.impl;

import com.gnaw.chord.service.ChordID;
import com.gnaw.chord.service.Entry;
import com.gnaw.chord.service.Node;
import com.gnaw.chord.service.NodeSet;

/**
 * Implementation of a Chord node.
 * 
 * @author Josh Tan
 *
 */
public class NodeImpl extends Node {
	
	private FingerTable fingertable;

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node findSuccessor(ChordID key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertEntry(Entry entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntry(Entry entry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyOfDeparture(Node predecessor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NodeSet checkPotentialPredecessor(Node potentialPredecessor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ping() {
		// TODO Auto-generated method stub
		
	}

}
