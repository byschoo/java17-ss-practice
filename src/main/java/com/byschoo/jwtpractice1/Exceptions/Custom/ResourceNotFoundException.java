package com.byschoo.jwtpractice1.Exceptions.Custom;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceNotFoundException extends RuntimeException {

    private final String code;
    private final String severity;
    private final HttpStatus httpStatus;

    public ResourceNotFoundException(String message) {
        super(message);
        this.code = null;
        this.severity = null;
        this.httpStatus = null;
    }

    public ResourceNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.code = null;
        this.severity = null;
        this.httpStatus = httpStatus;
    }

    public ResourceNotFoundException(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.severity = null;
        this.httpStatus = httpStatus;
    }

    public ResourceNotFoundException(String message, String code, String severity, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.severity = severity;
        this.httpStatus = httpStatus;
    }
}
