package com.johnebri.cutsession.exception;

import com.johnebri.cutsession.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author John on 12/15/22
 */

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        // get validation errors
        List<FieldError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new FieldError(fieldError.getObjectName(), fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        ErrorResponse er = new ErrorResponse();
        er.setMessage("Validate errors occurred");

        List<String> errs = new ArrayList();
        for(FieldError error : errors) {
            errs.add(error.getDefaultMessage());
        }

        er.setErrors(errs.toArray(new String[0]));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleDuplicateResourceException(DuplicateResourceException ex){
        ErrorResponse er = new ErrorResponse();
        er.setMessage(ex.getMessage());

        List<String> errs = new ArrayList();
        errs.add(ex.getMessage());
        er.setErrors(errs.toArray(new String[0]));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex){
        ErrorResponse er = new ErrorResponse();
        er.setMessage(ex.getMessage());

        List<String> errs = new ArrayList();
        errs.add(ex.getMessage());
        er.setErrors(errs.toArray(new String[0]));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultException(Exception ex){
        ErrorResponse er = new ErrorResponse();
        er.setMessage("Something went wrong");

        List<String> errs = new ArrayList();
        errs.add(ex.getMessage());
        er.setErrors(errs.toArray(new String[0]));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

}
