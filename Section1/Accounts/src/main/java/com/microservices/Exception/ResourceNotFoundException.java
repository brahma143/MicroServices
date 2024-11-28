package com.microservices.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resources, String fieldName, String fieldValue) {
        super(String.format("%s not found wit given input data %s", resources, fieldName, fieldValue));
    }
}
