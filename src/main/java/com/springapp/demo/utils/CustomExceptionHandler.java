package com.springapp.demo.utils;

import com.springapp.demo.customexceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomResponse<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException err) {

        Map<String, String> errors = new HashMap<>();

        err.getBindingResult().getAllErrors().forEach(error -> {

            errors.put(((FieldError) error).getField(), error.getDefaultMessage());
        });

        return new CustomResponse<>(HttpStatus.BAD_REQUEST, errors, "Bad Request");
    }

    @ExceptionHandler(NoSuchElementException.class)
    public CustomResponse<String> handleNoSuchElementException(NoSuchElementException err) {

        return new CustomResponse<>(HttpStatus.NOT_FOUND, null, "Not Found");
    }

    @ExceptionHandler(SQLException.class)
    public CustomResponse<String> handleSQLException(SQLException err) {

        return new CustomResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, err.toString(),  "Internal Server Error");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public CustomResponse<?> handleResourceNotFoundException(ResourceNotFoundException err) {

        return new CustomResponse<>(HttpStatus.NOT_FOUND, err.getMessage(), "Not Found");
    }
}
