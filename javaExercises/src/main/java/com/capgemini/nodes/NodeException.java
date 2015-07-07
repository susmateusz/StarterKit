package com.capgemini.nodes;
/**
 * Exceptions for nodeValidator
 * @author MATSUS
 *
 */
public class NodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * enum which identifies error code
	 */
	private NodeErrorCode errorCode;

	/**
	 * Exception constructor
	 * @param code enum of code
	 */
	public NodeException(NodeErrorCode code) {
		this.errorCode = code;
	}

	/**
	 * getter of error code
	 * @return error code
	 */
	public NodeErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * getter of error message
	 * @return error message
	 */
	public String getErrorMessage() {
		return "Error "+errorCode.getId()+" : "+errorCode.getMsg();
	}
}
