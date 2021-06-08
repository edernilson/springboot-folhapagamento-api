package com.edernilson.folhapagamento.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
@ResponseBody
public class GlobalControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseDefault> handleNoContent(BusinessException businessException) {
        ResponseDefault retorno = new ResponseDefault(businessException.getErrorCode(), businessException.getErrorMessage());

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        switch (retorno.getCode()) {
            case "01":
                httpStatus = HttpStatus.NOT_FOUND;
            case "10":
                httpStatus = HttpStatus.BAD_REQUEST;
            default:
                break;
        }
        return new ResponseEntity<>(retorno, httpStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ResponseDefault("Entidade não contém valores válidos", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ResponseEntity<>(new ResponseDefault("101", "Ocorreu erro de integridade. Outra informação depende desta."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException exception) {
        return new ResponseEntity<>(new ResponseDefault("101", "O token expirou. obtenha um novo token e tente novamente."), HttpStatus.BAD_REQUEST);
    }    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception) {
        return new ResponseEntity<>(new ResponseDefault("100", "Ocorreu algum erro não reconhecido"), HttpStatus.BAD_REQUEST);
    }    
    
}