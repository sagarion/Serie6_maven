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
public class NullParameterException extends Exception {

        /**
        * Creates a new instance of <code>NullParameterException</code> without
        * detail message.
        */
	public NullParameterException() {
            super();
	}

	/**
        * Constructs an instance of <code>NullParameterException</code> with the
        * specified detail message.
        *
        * @param message the detail message.
        */
	public NullParameterException(String message) {
            super(message);
	}
        
        /**
        * Constructs an instance of <code>NullParameterException</code> with the
        * specified detail message.
        *
        * @param message the detail message.
        * @param cause the cause of the exception.
        */
	public NullParameterException(String message, Throwable cause) {
            super(message, cause);
	}

}