package com.kikinep.forum.infra.error;

import org.springframework.validation.FieldError;

import java.sql.SQLIntegrityConstraintViolationException;

public record ExceptionResponseData(
        String message
) {
    public ExceptionResponseData(FieldError e) {
        this(e.getField() + " " + e.getDefaultMessage());
    }

    public ExceptionResponseData(SQLIntegrityConstraintViolationException e) {
        this(e.getMessage());
    }
}
