package com.trg.myRetail.model;


public class MyRetailResponse {

    
    public MyRetailResponse(String message){
        this.message=message;
    }
    
    public MyRetailResponse() {
    }

    private String message;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
