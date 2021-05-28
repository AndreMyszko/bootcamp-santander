package com.project.bootcamp.exceptions;

//Exception type class
public class BusinessException extends RuntimeException{
    //constructor
    public BusinessException(String message){
        super(message); //sending message propagation to upper class (RunTExc) and we have a message because it can return many types of messages
    }
    
}
