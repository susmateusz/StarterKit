package com.capgemini.nodes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class testNodes {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testCreateNodes() {
		Node n1 = new Node("A000", "First node, root of a tree.", "A000");
		Node n2 = new Node("B000", "Second node, added 2 files.", "A000");
		assertEquals("Node [id=A000, description=First node, root of a tree., predecessorId=A000]", n1.toString());
		assertEquals("Node [id=B000, description=Second node, added 2 files., predecessorId=A000]", n2.toString());
	}

	@Test
	public void testValidatorOfIdWhenWrong(){
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A123", "First node, root of a tree.", "A123"));
		nodes.add(new Node("B123", "Second node, added 2 files.", "A13"));
		nodes.add(new Node("C1", "Third node, removed sth from file a.", "B123"));
		NodeValidators validator = new NodeValidators();
		// exception.expect(NodeException.class);
		try{
		validator.validateID(nodes);
		fail("NodeException due to invalid Id should be thrown.");
		} catch (NodeException e){
			assertEquals(NodeErrorCode.INVALID_ID, e.getErrorCode());
		}
	}

	@Test
	public void testValidatorOfIdWhenGood() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A123"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B123"));
		NodeValidators validator = new NodeValidators();
		try {
			validator.validateID(nodes);
		} catch (NodeException e) {
			fail("Found error when every ID was good.");
		}
	}

	@Test
	public void testValidatorOfDescriptionWhenWrong() throws NodeException {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "ROOT"));
		nodes.add(new Node("B002",
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pena",
				"A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		NodeValidators validator = new NodeValidators();
		exception.expect(NodeException.class);
		validator.validateDescription(nodes);
	}

	@Test
	public void testValidatorOfDescriptionWhenGood() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "ROOT"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A123"));
		nodes.add(new Node("C003", "", "B123"));
		NodeValidators validator = new NodeValidators();
		try {
			validator.validateDescription(nodes);
		} catch (NodeException e) {
			fail("Found error when every description was good.");
		}
	}

	@Test
	public void testValidatorOfCyclesWhenWrong() throws NodeException {
		NodeValidators validator = new NodeValidators();
		List<Node> nodes1 = new ArrayList<Node>();
		nodes1.add(new Node("A001", "First node, root of a tree.", "D004"));
		nodes1.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes1.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes1.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes1.add(new Node("E005", "Second node which points to C.", "C003"));
		exception.expect(NodeException.class);
		validator.validateCycles(nodes1);
	}

	@Test
	public void testValidatorOfCyclesWhenGood() {
		NodeValidators validator = new NodeValidators();
		List<Node> nodes1 = new ArrayList<Node>();
		nodes1.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes1.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes1.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes1.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes1.add(new Node("E005", "Second node which points to C.", "C003"));
		try {
			validator.validateCycles(nodes1);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
		List<Node> nodes2 = new ArrayList<Node>();
		nodes2.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes2.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes2.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes2.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes2.add(new Node("E005", "Second node which points to C.", "D004"));
		try {
			validator.validateCycles(nodes2);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
	}

	@Test
	public void testValidatorOfPredecesssorsWhenFollowerInWrongPlace() throws NodeException {
		NodeValidators validator = new NodeValidators();
		List<Node> nodes1 = new ArrayList<Node>();
		nodes1.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes1.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes1.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes1.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes1.add(new Node("E005", "Second node which points to C.", "B002"));
		exception.expect(NodeException.class);
		validator.validatePredecessors(nodes1);
	}

	@Test
	public void testValidatorOfPredecesssorsWhenToMuchFollowers() throws NodeException {
		NodeValidators validator = new NodeValidators();
		List<Node> nodes1 = new ArrayList<Node>();
		nodes1.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes1.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes1.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes1.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes1.add(new Node("E005", "Second node which points to C.", "C002"));
		nodes1.add(new Node("F006", "Third node which points to C.", "C002"));
		exception.expect(NodeException.class);
		validator.validatePredecessors(nodes1);
	}

	@Test
	public void testValidatorOfPredecessorsWhenGood() {
		NodeValidators validator = new NodeValidators();
		List<Node> nodes1 = new ArrayList<Node>();
		nodes1.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes1.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes1.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes1.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes1.add(new Node("E005", "Second node which points to C.", "C003"));
		try {
			validator.validatePredecessors(nodes1);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
		List<Node> nodes2 = new ArrayList<Node>();
		nodes2.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes2.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes2.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes2.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes2.add(new Node("E005", "Second node which points to C.", "D004"));
		try {
			validator.validatePredecessors(nodes2);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
	}

}
