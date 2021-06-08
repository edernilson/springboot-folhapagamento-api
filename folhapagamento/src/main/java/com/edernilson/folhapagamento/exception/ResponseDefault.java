package com.edernilson.folhapagamento.exception;

public class ResponseDefault {

    private String code;
    private String message;

    public ResponseDefault() {
    }

    public ResponseDefault(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
