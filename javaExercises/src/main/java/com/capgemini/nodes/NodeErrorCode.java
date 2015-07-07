package com.capgemini.nodes;


/**
 * Enum which keeps error message and id. Using by NodeException to show
 * exceptions thrown by nodeValidator class.
 * Helpful readings: <br>
 * http://wiringtheplanet.blogspot.de/2012/10/use-enums-instead-of-int-constants-this.html<br>
 * https://northconcepts.com/blog/2013/01/18/6-tips-to-improve-your-exception-handling/<br>
 * @author MATSUS
 *
 */
public enum NodeErrorCode {
	/**
	 * Values of enum
	 */
	INVALID_ID(0, "ID of the node has invalid format."), INVALID_DESCRIPTION(1,
			"Description of the node is too long"), CYCLE(2,
					"Found cycle in the list of nodes."), INVALID_NUMBER_OF_SUBSEQUENT(3,
							"Found too much subsequents of a node in the list of nodes."), INVALID_SUBSEQUENT_POSITION(
									4, "Subsequent found in wrong place.");

	/**
	 * field keeps ID of the error
	 */
	private final int id;
	/**
	 * field keeps message of the error
	 */
	private final String msg;

	/**
	 * Constructor which takes 2 arguments - id and message.
	 * @param id id of the error
	 * @param msg test message of the error
	 */
	private NodeErrorCode(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	/**
	 * getter of ID
	 * @return error's ID
	 */
	public int getId() {
		return id;
	}

	/**
	 * getter of message
	 * @return error's text message
	 */
	public String getMsg() {
		return msg;
	}
}
