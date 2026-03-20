package com.gobdev.spring_mongodb_social_api.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(Object id) {
        super("No object found with Id '" + id + "'");
    }
}