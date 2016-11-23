package com.logicmonitor.spm.exception;

/**
 * Exception to indicate if something went wrong while interacting with the
 * database
 * 
 * @author Supraj
 *
 */
public class StorageException extends Exception {

	private static final long serialVersionUID = -1345430472318878681L;

	/**
	 * Default exception with no arguments
	 */
	public StorageException() {
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
	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Exception wrapped with only a message
	 * 
	 * @param message
	 *            The reason for the exception
	 */
	public StorageException(String message) {
		super(message);
	}

	/**
	 * Exception wrapped with only a cause
	 * 
	 * @param cause
	 *            The cause of the exception
	 */
	public StorageException(Throwable cause) {
		super(cause);
	}
}
