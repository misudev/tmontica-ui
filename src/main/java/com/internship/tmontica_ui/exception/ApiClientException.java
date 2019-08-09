package com.internship.tmontica_ui.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ApiClientException extends RuntimeException{
    private HttpStatus httpStatus;
    private Map responseBody;

    public ApiClientException(HttpStatus httpStatus , Map responseBody){
        this.httpStatus = httpStatus;
        this.responseBody = responseBody;
    }

}
