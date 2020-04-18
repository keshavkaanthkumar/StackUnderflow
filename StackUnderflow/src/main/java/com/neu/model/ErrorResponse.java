package com.neu.model;

import java.util.List;

/**
 * @author Keshav
 *
 */
public class ErrorResponse
{
    public ErrorResponse(List<Error> errors) {
        super();
       
        this.errors = errors;
    }
  
   


	private List<Error> errors;




	public List<Error> getErrors() {
		return errors;
	}




	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
 
    //getters and setters
}

