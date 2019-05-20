package com.trg.myRetail.Exception;

import org.springframework.http.HttpStatus;

public class MyRetailException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** The error message. */
    private String errorMessage = " Something went wrong.. \n please contact administrator";
    
    /** The http status. */
    private HttpStatus httpStatus;

    /**
     * Instantiates a new PMT exception.
     */
    public MyRetailException() {
    }

    /**
     * Instantiates a new MyRetailException.
     *
     * @param errMsg the err msg
     */
    public MyRetailException(String errMsg) {
        this.errorMessage = errMsg;
    }

    /**
     * Instantiates a new MyRetailException.
     *
     * @param errorMessage the error message
     * @param httpStatus the http status
     */
    public MyRetailException(String errorMessage,HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus=httpStatus;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the http status.
     *
     * @return the http status
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    /**
     * Sets the http status.
     *
     * @param httpStatus the new http status
     */
    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}


