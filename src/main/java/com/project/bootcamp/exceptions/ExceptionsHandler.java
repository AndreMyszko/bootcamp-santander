package com.project.bootcamp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//ControllerAdvice be like.. wow, look at me, I am an exception repository 
@ControllerAdvice //interceptor
public class ExceptionsHandler extends ResponseEntityExceptionHandler{

    //used to intercept 200OK when server conn is ok, but any rule was broke, sending exception
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handleSecurity(BusinessException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ExceptionResponse(e.getMessage())); //http JSON -> UNPROCESSABLE_ENTITY-Message = error 400 (rule error)
    }

    //used when is trying to return something that not exists
    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ExceptionResponse> handleSecurity(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage())); //http JSON -> UNPROCESSABLE_ENTITY-Message = error 400 (rule error)
    }


}
