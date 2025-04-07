package com.chalenge.sooft.adapter.web.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class BusinessException extends Exception {
    private final HttpStatus httpCode;
    private final ErrorResponse errorResponse;

    public BusinessException(HttpStatus httpStatus, ErrorResponse errorResponse) {
        this.httpCode = httpStatus;
        this.errorResponse = errorResponse;
    }

}
