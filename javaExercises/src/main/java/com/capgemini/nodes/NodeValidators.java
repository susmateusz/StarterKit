package com.capgemini.nodes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ldrygala on 2015-02-09.
 * <p/>
 * Write validate for
 * <ul>
 * <li>node id should have 4 characters</li>
 * <li>node description can have maximal 128 characters</li>
 * <li>no cycle</li>
 * <li>only penultimate can have two subsequent</li>
 * </ul>
 */
public class NodeValidators {

	/**
	 * Validates id of the nodes from list
	 * 
	 * @param nodes
	 *            list of nodes for validation
	 * @throws NodeException
	 *             thrown when id or predecessorId of the node has length
	 *             different from 4. Error code NodeErrorCode.INVALID_ID
	 */
	public void validateID(List<Node> nodes) throws NodeException {
		for (Node n : nodes)
			if (n.getId().length() != 4 || n.getPredecessorId().length() != 4)
				throw new NodeException(NodeErrorCode.INVALID_ID);
	}

	/**
	 * Validates descriptions of the nodes from list
	 * 
	 * @param nodes
	 *            list of the nodes for validation
	 * @throws NodeException
	 *             thrown when description of the node is longer than 128
	 *             characters. Error code NodeErrorCode.INVALID_DESCRIPTION
	 */
	public void validateDescription(List<Node> nodes) throws NodeException {
		for (Node n : nodes)
			if (n.getDescription().length() > 128)
				throw new NodeException(NodeErrorCode.INVALID_DESCRIPTION);
	}

	/**
	 * Checks if list of nodes has cycles. If the length of list has no HEAD
	 * node, then the cycle exist in the list. HEAD node is the node, which
	 * doesn't point for any other node.
	 * 
	 * @param nodes
	 * @throws NodeException
	 *             thrown when list of nodes has cycles
	 */
	public void validateCycles(List<Node> nodes) throws NodeException {
		// Checking if head of graph exist
		boolean headExist = false;
		for (Node n : nodes)
			if (n.getId().equals(n.getPredecessorId()))
				headExist = true;
		// if no, exception is thrown
		if (!headExist && !nodes.isEmpty())
			throw new NodeException(NodeErrorCode.CYCLE);
	}

	/**
	 * Check if structure of list is correct: Except the penultimate node, all
	 * the nodes from list must have one subsequent The penultimate node can
	 * have two subsequent The last nodes can have 0 subsequents
	 * 
	 * @param nodes
	 *            list of nodes
	 * @throws NodeException
	 *             thrown when list of nodes has incorrect structure
	 */
	public void validatePredecessors(List<Node> nodes) throws NodeException {
		// Map of predecessors: node => predecessor
		if (nodes.isEmpty())
			return;
		Map<Node, Node> predecessors = new HashMap<Node, Node>();
		for (Node i : nodes)
			for (Node j : nodes)
				if (i.getPredecessorId() == j.getId())
					predecessors.put(i, j);
		// Set of all nodes
		Set<Node> noFollowers = new HashSet<Node>(nodes);
		// Difference between all nodes and nodes which have followers is set of
		// the last nodes (they have no followers)
		noFollowers.removeAll(predecessors.values());
		// If number of them are neither 1 nor 2, throw exception
		if (noFollowers.size() != 1 && noFollowers.size() != 2)
			throw new NodeException(NodeErrorCode.INVALID_NUMBER_OF_SUBSEQUENT);
		// If both last nodes points at different node(penultimate node), then
		// exception is thrown
		String penultimateId = null;
		for (Node n : noFollowers)
			if (penultimateId == null)
				penultimateId = n.getPredecessorId();
			else if (!penultimateId.equals(n.getPredecessorId()))
				throw new NodeException(NodeErrorCode.INVALID_SUBSEQUENT_POSITION);
	}
}
