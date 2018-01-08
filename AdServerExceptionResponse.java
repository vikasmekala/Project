package com.myproject.campaign.model;

import org.springframework.http.HttpStatus;

import java.util.Date;


public class AdServerExceptionResponse {
    private String errorDescription;
    private int errorCode;
    private HttpStatus httpStatus;
    private Date timeStamp;

    public AdServerExceptionResponse() {

    }

    public AdServerExceptionResponse(String errorDescription, int errorCode, HttpStatus httpStatus){
        this.errorDescription = errorDescription;
        this.errorCode=errorCode;
        this.httpStatus =httpStatus;
        timeStamp = new Date();
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
