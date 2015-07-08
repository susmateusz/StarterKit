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
	public void shouldCreateNode() {
		Node n = new Node("A000", "First node, root of a tree.", "A000");
		assertEquals("Node [id=A000, description=First node, root of a tree., predecessorId=A000]", n.toString());
	}

	/**
	 * Checks if validator of ID correctly find errors when id or predecessor's
	 * id is incorrect
	 */
	@Test
	public void shouldThrownExceptionForIDEqualA1() {
		// given
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A1", "First node, root of a tree.", "A123"));
		NodeValidators validator = new NodeValidators();
		// then
		try {
			validator.validateID(nodes);
			fail("NodeException due to invalid Id should be thrown.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.INVALID_ID, e.getErrorCode());
		}
	}

	/**
	 * Checks if validator of ID correctly find errors when id or predecessor's
	 * id is incorrect
	 */
	@Test
	public void shouldThrownExceptionForPredecessorIDEqualA1() {
		// given
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A123", "First node, root of a tree.", "A1"));
		NodeValidators validator = new NodeValidators();
		// then
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
	public void shouldPassForIDEqualsA001() {
		// given
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		NodeValidators validator = new NodeValidators();
		// then
		try {
			validator.validateID(nodes);
		} catch (NodeException e) {
			fail("Found error when every ID was good.");
		}
	}

	/**
	 * Checks if validations fails when description is too long
	 */
	@Test
	public void shouldThrowExceptionForTooLongDescription() {
		// given
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("B002",
				"Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque pena",
				"A001"));
		NodeValidators validator = new NodeValidators();
		// then
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
	public void shouldPassForShortDescription() {
		// given
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "ROOT"));
		NodeValidators validator = new NodeValidators();
		// then
		try {
			validator.validateDescription(nodes);
		} catch (NodeException e) {
			fail("Found error when every description was good.");
		}
	}

	/**
	 * Checks if validation fails when list of nodes has cycles
	 */
	@Test
	public void shouldThrownExceptionForCycle() {
		// given
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "D004"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "C003"));
		NodeValidators validator = new NodeValidators();
		// then
		try {
			validator.validateCycles(nodes);
			fail("NodeException due to cycles in graph should be thrown.");
		} catch (NodeException e) {
			assertEquals(NodeErrorCode.CYCLE, e.getErrorCode());
		}
	}

	/**
	 * Checks if validation passes when list of nodes doesn't have cycles or
	 * list is empty
	 */
	@Test
	public void shouldPassWhenLinearAndPreLastHas2Children() {
		// given
		NodeValidators validator = new NodeValidators();
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "C003"));
		// then
		try {
			validator.validateCycles(nodes);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
	}

	/**
	 * Checks if validation passes when list of nodes doesn't have cycles or
	 * list is empty
	 */
	@Test
	public void shouldPassWhenLinear() {
		// given
		NodeValidators validator = new NodeValidators();
		List<Node> nodes = new ArrayList<Node>();
		nodes.add(new Node("A001", "First node, root of a tree.", "A001"));
		nodes.add(new Node("B002", "Second node, added 2 files.", "A001"));
		nodes.add(new Node("C003", "Third node, removed sth from file a.", "B002"));
		nodes.add(new Node("D004", "Node D, does nothing.", "C003"));
		nodes.add(new Node("E005", "Second node which points to C.", "D004"));
		// then
		try {
			validator.validateCycles(nodes);
		} catch (NodeException e) {
			fail("Found cycle when graph was good.");
		}
	}

	/**
	 * Checks if validation fails when followers of nodes are in wrong places
	 */
	@Test
	public void shouldThrownExceptionFor2ChildrenForB() {
		// given
		NodeValidators validator = new NodeValidators();
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
	}

	/**
	 * Checks if validation fails when followers of nodes are in wrong places
	 */
	@Test
	public void shouldThrownExceptionFor3ChildrenForC() {
		// given
		List<Node> nodes = new ArrayList<Node>();
		NodeValidators validator = new NodeValidators();
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
	 * Checks if validation passes when list of nodes is correct or list is
	 * empty
	 */
	@Test
	public void shouldPassForGoodStructureWith2ChildrenOfPenultimate() {
		// given
		NodeValidators validator = new NodeValidators();
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
	}
}
