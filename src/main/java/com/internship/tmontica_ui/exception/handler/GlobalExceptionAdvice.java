package com.internship.tmontica_ui.exception.handler;

import com.internship.tmontica_ui.exception.ApiClientException;
import com.internship.tmontica_ui.exception.ApiServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionAdvice {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionAdvice.class);


    @ExceptionHandler(ApiClientException.class)
    public ResponseEntity handleApiClientException(ApiClientException e){
        return ResponseEntity.status(e.getHttpStatus()).body(e.getResponseBody());
    }

    @ExceptionHandler(ApiServerException.class)
    public ResponseEntity handleApiServerException(ApiServerException e){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("API server error");
    }

}
