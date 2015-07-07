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
	public void validateID(List<Node> nodes) throws NodeException {
		for (Node n : nodes)
			if (n.getId().length() != 4 || n.getPredecessorId().length() != 4)
				throw new NodeException(NodeErrorCode.INVALID_ID);
	}
	
	public void validateDescription(List<Node> nodes) throws NodeException {
		for (Node n : nodes)
			if (n.getDescription().length() > 128)
				throw new NodeException(NodeErrorCode.INVALID_DESCRIPTION);
	}
	
	/**
	 * If number of nodes is equal to number of edges, then there is a graph in a cycle
	 * @param nodes
	 * @throws NodeException
	 */
	public void validateCycles(List<Node> nodes) throws NodeException {
		// Checking if head of graph exist
		boolean headExist = false;
		for (Node n : nodes)
			if(n.getId().equals(n.getPredecessorId()))
				headExist = true;
		if( !headExist )
			throw new NodeException(NodeErrorCode.INVALID_DESCRIPTION);
	}
	
	public void validatePredecessors(List<Node> nodes) throws NodeException {
		// find how much nodes are not pointed by anybody
		Map<Node, Node> predecessors = new HashMap<Node, Node>();
		for(Node i : nodes)
			for(Node j : nodes)
				if(i.getPredecessorId() == j.getId())
					predecessors.put(i, j);
		Set<Node> noFollowers = new HashSet<Node>(nodes);
		noFollowers.removeAll(predecessors.values());
		for(Node n : noFollowers)
			System.out.println(n);
		System.out.println();
		// if number of them are larger than 2 throw exception
		if( noFollowers.size()!=1 && noFollowers.size()!=2)
			throw new NodeException(NodeErrorCode.INVALID_NUMBER_OF_SUBSEQUENT);
		// if their parents are different throw exception
		String penultimateId=null;
		for(Node n : noFollowers)
			if(penultimateId==null)
				penultimateId=n.getPredecessorId();
			else if(!penultimateId.equals(n.getPredecessorId()))
				throw new NodeException(NodeErrorCode.INVALID_NUMBER_OF_SUBSEQUENT);
	}
}


