package com.capgemini.nodes;

public class NodeException extends Exception {

	private static final long serialVersionUID = 1L;

	private NodeErrorCode errorCode;

	public NodeException(NodeErrorCode code) {
		this.errorCode = code;
	}

	public NodeErrorCode getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorCode.getMsg();
	}
}
