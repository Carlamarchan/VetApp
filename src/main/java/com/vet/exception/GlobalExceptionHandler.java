package com.vet.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Handler that manages the exceptions thrown in the application
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler method of MethodArgumentNotValidException exception
     *
     * @param ex      The exception to handle
     * @param headers The headers to be written to the response
     * @param status  The selected response status
     * @param request The current request
     * @return Bad request response with errors messages
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handler method of DuplicatedDniException exception
     *
     * @param ex The exception to handle
     * @return Bad request response with an error message
     */
    @ExceptionHandler(DuplicatedDniException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicatedDniException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Duplicated DNI error occurred. Please provide a unique DNI.");
    }

    /**
     * Handler method of OwnerNotFoundException exception
     *
     * @param ex The exception to handle
     * @return Not found response with an error message
     */
    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<String> handleOwnerNotFound(OwnerNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("That owner with the given ID does not exist");
    }

}
