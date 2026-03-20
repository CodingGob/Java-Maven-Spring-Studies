package com.gobdev.spring_mongodb_social_api.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Object id) {
        super("No Objects found with Id '" + id + "'");
    }
}