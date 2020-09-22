package com.validus.music.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.validus.music.controllers.Exception.ResourceNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//	  @Override
//	  @ExceptionHandler(ConstraintViolationException.class)
//	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//	      HttpHeaders headers, HttpStatus status, WebRequest request) {
//	    ErrorResponse ErrorResponse = new ErrorResponse(new Date(), "Validation Failed",
//	        ex.getBindingResult().toString());
//	    return new ResponseEntity(ErrorResponse, HttpStatus.BAD_REQUEST);
//	  } 
	  
	    @ExceptionHandler(ConstraintViolationException.class)
	    public void constraintViolationException(HttpServletResponse response, ConstraintViolationException e) throws IOException {
	        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
	    }

	  

	  @ExceptionHandler(ResourceNotFoundException.class)
	  public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	    ErrorResponse ErrorResponse = new ErrorResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity(ErrorResponse, HttpStatus.NOT_FOUND);
	  }

	  
	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	    ErrorResponse ErrorResponse = new ErrorResponse(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity(ErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  
}