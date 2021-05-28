package com.project.bootcamp.exceptions;

//when we have an error, we need a response message... in this case this.string
public class ExceptionResponse {

    private String message;

    //constructor to propagate message 
    public ExceptionResponse(String message){
        this.message = message; //working like "set" statment 
    }

    //getter only
    public String getMessage() {
        return this.message;
    }

}
