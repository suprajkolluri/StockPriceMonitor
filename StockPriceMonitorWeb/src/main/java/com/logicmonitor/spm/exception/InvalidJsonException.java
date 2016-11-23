package com.logicmonitor.spm.exception;

/**
 * Exception to indicate the JSON returned from the Stock Information provider
 * is invalid
 * 
 * @author Supraj
 *
 */
public class InvalidJsonException extends Exception {
	private static final long serialVersionUID = -8331154760495655486L;

	/**
	 * Default exception with no arguments
	 */
	public InvalidJsonException() {
		super();
	}

	/**
	 * Exception with a cause and a message
	 * 
	 * @param message
	 *            The reason for the exception
	 * @param cause
	 *            The cause of the exception
	 */
	public InvalidJsonException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Exception wrapped with only a message
	 * 
	 * @param message
	 *            The reason for the exception
	 */
	public InvalidJsonException(String message) {
		super(message);
	}

	/**
	 * Exception wrapped with only a cause
	 * 
	 * @param cause
	 *            The cause of the exception
	 */
	public InvalidJsonException(Throwable cause) {
		super(cause);
	}

}
