package com.example.restdemo.web;

import com.example.restdemo.exception.EntityNotFoundException;
import com.example.restdemo.exception.InvalidEntityException;
import com.example.restdemo.model.ErrorResponse;
import com.google.gson.stream.MalformedJsonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice("demos.springdata.restdemo.web")
@Slf4j
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }

    @ExceptionHandler({InvalidEntityException.class, ConstraintViolationException.class,
            HttpMessageConversionException.class, MalformedJsonException.class})
    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResponse(ex.getClass().getSimpleName(), ex.getMessage()));
    }
}
