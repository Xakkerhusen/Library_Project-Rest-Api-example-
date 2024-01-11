package com.example.Library_Spring_Boot.controller;

import com.example.Library_Spring_Boot.exp.AppBadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = {AppBadException.class})
    public ResponseEntity<?> handle(AppBadException appBadException) {
        return ResponseEntity.badRequest().body(appBadException.getMessage());
    }
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handle(RuntimeException runtimeException) {
        return ResponseEntity.badRequest().body(runtimeException.getMessage());
    }


}
