package com.gobdev.spring_mongodb_social_api.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gobdev.spring_mongodb_social_api.services.exceptions.ObjectNotFoundException;
import com.gobdev.spring_mongodb_social_api.services.exceptions.UniqueViolationException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Object not found";

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            error,
            e.getMessage(),
            request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(UniqueViolationException.class)
    public ResponseEntity<StandardError> uniqueViolation(UniqueViolationException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String error = "Data conflict";

        StandardError err = new StandardError(
            Instant.now(),
            status.value(),
            error,
            e.getMessage(),
            request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(422); // UNPROCESSABLE_ENTITY
        String error = "Validation error";
        
        ValidationError err = new ValidationError(
            Instant.now(), 
            status.value(), 
            error, 
            "Validation error in fields", 
            request.getRequestURI());
        
        for (FieldError fe : e.getBindingResult().getFieldErrors()) {
            err.addError(fe.getField(), fe.getDefaultMessage());
        }
        
        return ResponseEntity.status(status).body(err);
    }
}