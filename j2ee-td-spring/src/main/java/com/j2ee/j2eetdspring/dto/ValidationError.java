package com.j2ee.j2eetdspring.dto;

import java.beans.JavaBean;
import java.util.HashMap;
import java.util.Map;

@JavaBean
public class ValidationError {

    private String errorMessage;
    private Map<String, String> fieldErrors;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    public void addFielsErrors(String field, String message){

        if (this.fieldErrors == null){
            this.fieldErrors = new HashMap<>();
        }
        this.fieldErrors.put(field, message);

    }

}
