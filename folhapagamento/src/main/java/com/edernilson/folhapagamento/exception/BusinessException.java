package com.edernilson.folhapagamento.exception;

public class BusinessException extends RuntimeException {

    private String errorCode;
    private String errorMessage;
    
    public BusinessException() {
    }

    public BusinessException(String code, String message) {
        this.setErrorCode(code);
        this.setErrorMessage(message);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
