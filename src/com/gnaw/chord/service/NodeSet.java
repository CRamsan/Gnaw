package com.gnaw.chord.service;

import java.util.List;

/**
 * Set of nodes a particular node is linked to.
 * 
 * @author Josh Tan
 *
 */
public class NodeSet {
	
	private Node predecessor;
	private List<Node> successors;
	
	/**
	 * Get the predecessor node.
	 * @return 	the predecessor node
	 */
	public Node getPredecessor() {
		return predecessor;
	}
	
	/**
	 * Set the predecessor node.
	 * @param predecessor	the predecessor node
	 */
	public void setPredecessor(Node predecessor) {
		this.predecessor = predecessor;
	}
	
	/**
	 * Get the ordered list of successor nodes.
	 * @return	the ordered list of successor nodes
	 */
	public List<Node> getSuccessors() {
		return successors;
	}
	
	/**
	 * Set the ordered list of successor nodes.
	 * @param successors	the ordered list of successor nodes
	 */
	public void setSuccessors(List<Node> successors) {
		this.successors = successors;
	}
	
}
