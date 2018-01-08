package com.myproject.campaign.exception;

import com.myproject.campaign.model.AdServerExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerService extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AdServerException.class)
    public ResponseEntity<AdServerExceptionResponse> handleAdServerExeption(AdServerException ex) {
        AdServerExceptionResponse adServerExceptionResponse = new AdServerExceptionResponse(ex.getMessage(),HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<AdServerExceptionResponse>(adServerExceptionResponse,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdServerExceptionResponse> handleAdServerExeption(Exception ex) {
        AdServerExceptionResponse adServerExceptionResponse = new AdServerExceptionResponse(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<AdServerExceptionResponse>(adServerExceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
