/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chalenge.sooft.adapter.web.exception;

import lombok.Data;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Data
public class ErrorResponse {

    private Date timestamp;
    private String message;
    private List<String> details;
    private String stacktrace;

    public ErrorResponse(String message, List<String> details, String stacktrace) {
        super();
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
        this.stacktrace = stacktrace;
    }

    public ErrorResponse(String message, List<String> details) {
        super();
        this.timestamp = new Date();
        this.message = message;
        this.details = details;
        this.stacktrace = "";
    }

    public ErrorResponse(String message) {
        super();
        this.timestamp = new Date();
        this.message = message;
        this.details = Arrays.asList("");
        this.stacktrace = "";
    }

}
