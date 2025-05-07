package com.byschoo.jwtpractice1.Exceptions.Custom;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceAlreadyExistsException extends RuntimeException {

    private final String code;
    private final String severity;
    private final HttpStatus httpStatus;
    
    public ResourceAlreadyExistsException(String message) {
        super(message);
        this.code = null;
        this.severity = null;
        this.httpStatus = null;
    }

    public ResourceAlreadyExistsException(String message, HttpStatus httpStatus) {
        super(message);
        this.code = null;
        this.severity = null;
        this.httpStatus = httpStatus;
    }
    
    public ResourceAlreadyExistsException(String message, String code, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.severity = null;
        this.httpStatus = httpStatus;
    }

    public ResourceAlreadyExistsException(String message, String code, String severity, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.severity = severity;
        this.httpStatus = httpStatus;
    }    

}
