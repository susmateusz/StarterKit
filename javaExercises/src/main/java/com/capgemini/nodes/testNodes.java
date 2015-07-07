package com.capgemini.nodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test validators of nodes
 * 
 * @author MATSUS
 *
 */
public class testNodes {

	/**
	 * Check if nodes are correctly created. No checking and field.
	 */
	@Test
	public void testCreateNodes() {
		Node n1 = new Node("A000", "First node, root of a tree.", "A000");
		Node n2 = new Node("B000", "Second node, added 2 files.", "A000");
		assertEquals("Node [id=A000, description=First node, root of a tree., predecessorId=A000]", n1.toString());
		assertEquals("Node [id=B000, description=Second node, added 2 files., predecessorId=A000]", n2.toString());
	}

	/**
	 * Checks if validator of ID correctly find errors when id or predecessor's
	 * id is incorrect
	 */
	@Test
	public void testValidatorOfIdWhenWrong() {
		// error in id of node
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A123", "First node, root of a tree.", "A123"));
		nodes.add(new Node("B12", "Second node, added 2 files.", "A123"));
		NodeValidators validator = new NodeValidators();
		try {
			validator.validateID(nodes);
			fail("NodeException due to invalid Id should be thrown.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.INVALID_ID, e.getErrorCode());
		}
		// error in predecessor's id of the node
		nodes = new ArrayList<Node>();
		nodes.add(new Node("A123", "First node, root of a tree.", "A123"));
		nodes.add(new Node("B123", "Second node, added 2 files.", "A12"));
		try {
			validator.validateID(nodes);
			fail("NodeException due to invalid Id should be thrown.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.INVALID_ID, e.getErrorCode());
		}
	}

	/**
	 * Checks if validation passes when nodes are correct or list is empty
	 */
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
		// Test when list is empty
		nodes = new ArrayList<Node>();
		try {
			validator.validateID(nodes);
		} catch (NodeException e) {
			fail("Found error with id when list was empty.");
		}
	}

	/**
	 * Checks if validations fails when description is too long
	 */
	@Test
	public void testValidatorOfDescriptionWhenWrong() {
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "ROOT"));
		nodes.add(new Node("B002",
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pena",
				"A001"));
		NodeValidators validator = new NodeValidators();
		try {
			validator.validateDescription(nodes);
			fail("NodeException due to invalid length of description should be thrown.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.INVALID_DESCRIPTION, e.getErrorCode());
		}
	}

	/**
	 * Checks if validation passes when nodes are correct or list is empty
	 */
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
		// test if list is empty
		nodes = new ArrayList<Node>();
		try {
			validator.validateDescription(nodes);
		} catch (NodeException e) {
			fail("Found error in descriptions when list was empty.");
		}
	}

	/**
	 * Checks if validation fails when list of nodes has cycles
	 */
	@Test
	public void testValidatorOfCyclesWhenWrong() {
		List<Node> nodes1 = new ArrayList<Node>();
		nodes1.add(new Node("A001", "First node, root of a tree.", "D004"));
		nodes1.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes1.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes1.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes1.add(new Node("E005", "Second node which points to C.", "C003"));
		NodeValidators validator = new NodeValidators();
		try {
			validator.validateCycles(nodes1);
			fail("NodeException due to cycles in graph should be thrown.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.CYCLE, e.getErrorCode());
		}
	}

	/**
	 * Checks if validation passes when list of nodes doesn't have cycles or list is empty
	 */
	@Test
	public void testValidatorOfCyclesWhenGood() {
		NodeValidators validator = new NodeValidators();
		// test of first example of list
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "C003"));
		try {
			validator.validateCycles(nodes);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
		// test of second example of list
		nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "D004"));
		try {
			validator.validateCycles(nodes);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
		nodes = new ArrayList<Node>();
		try {
			validator.validateCycles(nodes);
		} catch (NodeException e) {
			fail("Found error with cycles when list was empty.");
		}
	}

	/**
	 * Checks if validation fails when followers of nodes are in wrong places
	 */
	@Test
	public void testValidatorOfPredecesssorsWhenFollowerInWrongPlace() {
		NodeValidators validator = new NodeValidators();
		// first example
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "B002"));
		try {
			validator.validatePredecessors(nodes);
			fail("NodeException should be thrown due to subsequent in wrong position.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.INVALID_SUBSEQUENT_POSITION, e.getErrorCode());
		}
		// second example
		nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "C002"));
		nodes.add(new Node("F006", "Third node which points to C.", "C002"));
		try {
			validator.validatePredecessors(nodes);
			fail("NodeException should be thrown due to invalid number of subsequent for penultimate node.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.INVALID_NUMBER_OF_SUBSEQUENT, e.getErrorCode());
		}
	}

	/**
	 * Checks if validation passes when list of nodes is correct or list is empty
	 */
	@Test
	public void testValidatorOfPredecessorsWhenGood() {
		NodeValidators validator = new NodeValidators();
		// first example
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "C003"));
		try {
			validator.validatePredecessors(nodes);
		} catch (NodeException e) {
			fail("Found error when structure of list was correct.");
		}
		// second example
		nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "D004"));
		try {
			validator.validatePredecessors(nodes);
		} catch (NodeException e) {
			fail("Found cycle when structure of list was correct.");
		}
		// test when list is empty
		nodes = new ArrayList<Node>();
		try {
			validator.validatePredecessors(nodes);
			System.out.println("Exception wasn't thrown");
		} catch (NodeException e) {
			System.out.println(e.getErrorCode());
		}
		
	}

}
