package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ResourceNotFound.class})
    public ResponseEntity<?> resouceNotFoundException(ResourceNotFound ex, WebRequest request){
        Map<String,Object> exception = new HashMap<>();
        exception.put("time",new Date());
        exception.put("path",request.getDescription(false));
        exception.put("message",ex.getMessage());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> exception (MethodArgumentNotValidException ex){
        Map<String, String> error= new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(err ->
                {
                    String fields = ((FieldError)err).getField();
                    String messgae = err.getDefaultMessage();
                    error.put(fields,messgae);
                }
        );
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
