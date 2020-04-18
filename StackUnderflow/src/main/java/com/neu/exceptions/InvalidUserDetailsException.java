package com.neu.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Keshav
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserDetailsException extends RuntimeException{
	  private static final long serialVersionUID = 1L;
	  
	    public InvalidUserDetailsException(String message) {
	        super(message);
	    }
}

