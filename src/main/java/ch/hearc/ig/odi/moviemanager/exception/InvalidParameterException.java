/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.odi.moviemanager.exception;

/**
 *
 * @author thibault.daucourt
 */
public class InvalidParameterException extends Exception {

        /**
        * Creates a new instance of <code>InvalidParameterException</code> without
        * detail message.
        */
	public InvalidParameterException() {
            super();
	}

	/**
        * Constructs an instance of <code>InvalidParameterException</code> with the
        * specified detail message.
        *
        * @param message the detail message.
        */
	public InvalidParameterException(String message) {
            super(message);
	}

	/**
        * Constructs an instance of <code>InvalidParameterException</code> with the
        * specified detail message.
        *
        * @param message the detail message.
        * @param cause the cause of the exception.
        */
	public InvalidParameterException(String message, Throwable cause) {
            super(message, cause);
	}

}