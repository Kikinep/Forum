package com.kikinep.forum.infra.error;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> handleEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponseData>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ExceptionResponseData> errors = e.getFieldErrors().stream().map(ExceptionResponseData::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionResponseData> handleSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponseData(e));
    }
}
