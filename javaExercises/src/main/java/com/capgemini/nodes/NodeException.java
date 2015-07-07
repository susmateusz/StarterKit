package com.capgemini.nodes;

public class NodeException extends Exception {

	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String errorMsg;

	public NodeException(NodeErrorCode code) {
		this.errorCode = code.getId();
		this.errorMsg = code.getMsg();
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMsg;
	}
}
