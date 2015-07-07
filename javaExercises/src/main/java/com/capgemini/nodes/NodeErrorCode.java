package com.capgemini.nodes;

// http://wiringtheplanet.blogspot.de/2012/10/use-enums-instead-of-int-constants-this.html
// https://northconcepts.com/blog/2013/01/18/6-tips-to-improve-your-exception-handling/
public enum NodeErrorCode {
	INVALID_ID(0, "ID of the node has invalid format."), INVALID_DESCRIPTION(1,
			"Description of the node is too long"), CYCLE(2,
					"Found cycle in the list of nodes."), INVALID_NUMBER_OF_SUBSEQUENT(3,
							"Found too much subsequents of a node in the list of nodes."),
	INVALID_SUBSEQUENT_POSITION(4,"Subsequent found in wrong place.");

	private final int id;
	private final String msg;

	private NodeErrorCode(int id, String msg) {
		this.id = id;
		this.msg = msg;
	}

	public int getId() {
		return id;
	}

	public String getMsg() {
		return msg;
	}
}
