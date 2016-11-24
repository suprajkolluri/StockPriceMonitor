package com.logicmonitor.spm.exception;

/**
 * Exception to indicate that a symbol is not registered with any stock
 * 
 * @author Supraj
 * 
 */
public class InvalidSymbolException extends Exception {

	private static final long serialVersionUID = -4731037794358693529L;

	/**
	 * Default exception with no arguments
	 */
	public InvalidSymbolException() {
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
	public InvalidSymbolException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Exception wrapped with only a message
	 * 
	 * @param message
	 *            The reason for the exception
	 */
	public InvalidSymbolException(String message) {
		super(message);
	}

	/**
	 * Exception wrapped with only a cause
	 * 
	 * @param cause
	 *            The cause of the exception
	 */
	public InvalidSymbolException(Throwable cause) {
		super(cause);
	}
}
