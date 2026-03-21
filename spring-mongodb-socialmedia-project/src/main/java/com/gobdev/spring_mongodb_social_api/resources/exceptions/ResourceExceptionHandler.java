package com.gobdev.spring_mongodb_social_api.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}