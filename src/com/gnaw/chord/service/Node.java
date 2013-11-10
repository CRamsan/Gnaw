package com.gnaw.chord.service;

import java.net.URL;
import java.util.Set;

/**
 * A node in the Chord network.
 * 
 * @author Josh Tan
 *
 */
public abstract class Node {
	
	private ChordID id;
	private URL url;
	private NodeSet nodeSet;
	private Set<Entry> entries;
	
	/**
	 * Disconnect from the network.
	 */
	public abstract void disconnect();
	
	/**
	 * Find the successor node responsible for the given key.
	 * @param key	the key to search for
	 * @return	the responsible node
	 */
	public abstract Node findSuccessor(ChordID key);
	
	/**
	 * Insert an entry into this node.
	 * @param entry	the node to insert
	 */
	public abstract void insertEntry(Entry entry);
	
	/**
	 * Remove an entry from this node.
	 * @param entry	the entry to remove
	 */
	public abstract void removeEntry(Entry entry);
	
	/**
	 * Notify node that its predecessor has left the network.
	 * @param predecessor	the departed predecessor
	 */
	public abstract void notifyOfDeparture(Node predecessor);
	
	/**
	 * Notify node of a new potential predecessor and (potentially update) set of nodes for
	 * for this node.
	 * @param potentialPredecessor 	potential new predecessor for this node
	 * @return						the (potentially updated) nodeset for this node
	 */
	public abstract NodeSet checkPotentialPredecessor(Node potentialPredecessor);
	
	
	/**
	 * Request a sign of liveliness from this node.
	 */
	public abstract void ping();

	/**
	 * Get the node set for this node. Contains predecessor and successors.
	 * @return	the node set
	 */
	public NodeSet getNodeSet() {
		return nodeSet;
	}

	/**
	 * Set the node set for this node. 
	 * @param nodeSet	the node set to set
	 */
	public void setNodeSet(NodeSet nodeSet) {
		this.nodeSet = nodeSet;
	}

	/**
	 * Get the set of entries for this node.
	 * @return	the set of entries
	 */
	public Set<Entry> getEntries() {
		return entries;
	}

	/**
	 * Set the entry set for this node.
	 * @param entries	the entry set to set
	 */
	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}

}
