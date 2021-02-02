package com.example.demo.restcontrollers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler{
	
	@ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
        errors.put(error.getField(), error.getDefaultMessage()));       
        System.out.println(ex.getBindingResult().getFieldError());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
