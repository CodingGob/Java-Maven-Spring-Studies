package com.gobdev.spring_mongodb_social_api.services.exceptions;

public class UniqueViolationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UniqueViolationException(String msg) {
        super(msg);
    }
}
