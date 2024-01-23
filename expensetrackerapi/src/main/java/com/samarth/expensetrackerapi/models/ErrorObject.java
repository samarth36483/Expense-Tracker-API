package com.samarth.expensetrackerapi.models;

import lombok.Data;

import java.util.Date;
@Data
public class ErrorObject {
    private Integer statuscode;
    private String message;
    private Date timestamp;
}
