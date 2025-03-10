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
    public ResponseEntity<String> handleDuplicatedKeyException(DuplicatedDniException ex) {
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
                .body("The owner with the given ID does not exist");
    }

    /**
     * Handler method of PetNotFoundException exception
     *
     * @param ex The exception to handle
     * @return Not found response with an error message
     */
    @ExceptionHandler(PetNotFoundException.class)
    public ResponseEntity<String> handlePetNotFound(PetNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("The pet with the given ID does not exist");
    }

    /**
     * Handler method of DuplicatedChipNumberException exception
     *
     * @param ex The exception to handle
     * @return Bad request response with an error message
     */
    @ExceptionHandler(DuplicatedChipNumberException.class)
    public ResponseEntity<String> handleDuplicatedKeyException(DuplicatedChipNumberException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Duplicated Chip Number error occurred. Please provide a unique Chip Number.");
    }

    /**
     * Handler method of VaccineNotFoundException exception
     *
     * @param ex The exception to handle
     * @return Not found response with an error message
     */
    @ExceptionHandler(VaccineNotFoundException.class)
    public ResponseEntity<String> handleVaccineNotFound(VaccineNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("The vaccine with the given ID does not exist");
    }

    /**
     * Handler method of DuplicatedVaccineNameException exception
     *
     * @param ex The exception to handle
     * @return Bad request response with an error message
     */
    @ExceptionHandler(DuplicatedVaccineNameException.class)
    public ResponseEntity<String> handleDuplicatedNameException(DuplicatedVaccineNameException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Duplicated name error occurred. Please provide a unique vaccine name.");
    }

    /**
     * Handler method of VaccineRegisterNotFoundException exception
     * @param ex The exception to handle
     * @return Not found response with an error message
     */
    @ExceptionHandler(VaccineRegisterNotFoundException.class)
    public ResponseEntity<String> handleVaccineRegisterNotFound(VaccineRegisterNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("The vaccine register with the given ID does not exist");
    }

}
