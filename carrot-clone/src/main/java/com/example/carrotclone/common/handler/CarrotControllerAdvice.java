package com.example.carrotclone.common.handler;

import com.example.carrotclone.common.dto.CarrotResponse;
import com.example.carrotclone.common.exception.CarrotException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class CarrotControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<CarrotResponse<?>> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.info("NoHandlerFoundException");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(CarrotResponse.error(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CarrotResponse<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("HttpRequestMethodNotSupportedException");
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(CarrotResponse.error(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage()));
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<CarrotResponse<?>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        log.info("MissingRequestHeaderException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CarrotResponse.error(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CarrotResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("MethodArgumentNotValidException");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CarrotResponse.error(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(CarrotException.class)
    public ResponseEntity<CarrotResponse<?>> handleNewSnackException(CarrotException e) {
        log.info("CarrotException");
        return ResponseEntity.status(e.getHttpStatusCode())
                .body(CarrotResponse.error(e.getFailureCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CarrotResponse<?>> handleException(Exception e) {
        log.info(String.valueOf(e.getClass()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CarrotResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

}
