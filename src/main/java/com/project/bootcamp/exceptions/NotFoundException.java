package com.project.bootcamp.exceptions;

import com.project.bootcamp.util.MessageUtils;

public class NotFoundException extends RuntimeException{
    //constructor
    public NotFoundException(){
        super(MessageUtils.NO_RECORD_FOUND); //in this case we have every time the same return "not found"
    }
    
}
