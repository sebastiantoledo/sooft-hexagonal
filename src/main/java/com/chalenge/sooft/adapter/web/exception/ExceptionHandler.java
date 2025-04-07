package com.chalenge.sooft.adapter.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> responseException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return new ResponseEntity(new ErrorResponse("Fatal server error"
                , Arrays.asList(e.getLocalizedMessage())
                , sw.toString())
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> responseBusinessException(BusinessException e) {
        return new ResponseEntity(e.getErrorResponse(), e.getHttpCode());
    }

    // Validation error.
    @org.springframework.web.bind.annotation.ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        // List errors
        List<String> errors = fieldErrors.stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity(new ErrorResponse("Validation fail",errors), HttpStatus.BAD_REQUEST);
    }
}
