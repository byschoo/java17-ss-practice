package com.byschoo.jwtpractice1.Exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.byschoo.jwtpractice1.Exceptions.Custom.ResourceNotFoundException;
import com.byschoo.jwtpractice1.Exceptions.Response.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {

        Map<String, String> mapError = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                
                String key = ((FieldError) error).getField();
                String value = error.getDefaultMessage();

                mapError.put(key, value);
            });

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message(ex.getMessage())
                .mapError(mapError)
                .code(null)
                .object(getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.BAD_REQUEST
        );
    }






    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest webRequest) {
        
    //     Map<String, String> mapErrors = new HashMap<>();
    //     ex.getBindingResult().getAllErrors()
    //         .forEach((error) -> {
    //             String clave = ((FieldError) error).getField();
    //             String valor = error.getDefaultMessage();
    //             mapErrors.put(clave, valor);
    //         });
        
    // log.error("Exception Occurred: " + ex.getClass().getSimpleName() + " - " + mapErrors, ex);

    //     return new ResponseEntity<>(
    //         ErrorResponse.builder()
    //             .message("Validation error in entered data")
    //             .mapError(mapErrors)
    //             .code(null)
    //             .object(ex.getClass().getSimpleName())
    //             .severity(null)
    //             .url(webRequest.getDescription(false).replace("uri=", ""))
    //             .dateTime(LocalDateTime.now())
    //             .build(),
    //         HttpStatus.BAD_REQUEST // Devuelve 400
    //     );
    // }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
    log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message(ex.getMessage())
                .code(ex.getCode()) // Code es distinto en cada capa
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.NOT_FOUND // Devuelve 404
        );
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessgeNotReadableException(HttpMessageNotReadableException ex, WebRequest webRequest) {
    log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message(ex.getMessage())
                .code(null)
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.BAD_REQUEST // Devuelve 400
        );
    }

        @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest webRequest) {
        log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message(ex.getMessage())                
                .code(null)
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest webRequest) {
        log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message("Error saving data due to an integrity constraint. The record may already exist or be missing required data.")
                .code(null)
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.CONFLICT // Devuelve 409
        );
    }

    
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ErrorResponse> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest webRequest) {
        log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message(ex.getMessage())
                .code(null)
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.NOT_FOUND // Devuelve 404
        );
    }

    
    @ExceptionHandler(DataAccessResourceFailureException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessResourceFailureException(DataAccessResourceFailureException ex, WebRequest webRequest) {
        log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message(ex.getMessage())
                .code(null)
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.SERVICE_UNAVAILABLE // Devuelve 503
        );
    }


    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<ErrorResponse> handleCannotCreateTransactionException(CannotCreateTransactionException ex, WebRequest webRequest) {
        log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message(ex.getMessage())
                .code(null)
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR // Devuelve 500
        );
    }


    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception ex, WebRequest webRequest) {
    log.error("Exception Occured: " + ex.getClass().getSimpleName() + " - " + ex.getMessage(), ex);

        return new ResponseEntity<>(
            ErrorResponse.builder()
                .message("An unexpected error occurred on the server.")
                .code(null)
                .object(ex.getClass().getSimpleName())
                .severity(null)
                .url(webRequest.getDescription(false).replace("uri=", ""))
                .dateTime(LocalDateTime.now())
                .build(),
            HttpStatus.INTERNAL_SERVER_ERROR // Devuelve 500
        );
    }

}
