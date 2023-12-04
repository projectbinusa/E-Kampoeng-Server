package com.e_kampoeng.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<?> InternalError(InternalErrorException internalErrorException) {
        return ResponseHelper.error(internalErrorException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
