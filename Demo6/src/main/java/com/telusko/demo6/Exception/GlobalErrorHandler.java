package com.telusko.demo6.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {
      @ExceptionHandler
          public ResponseEntity<String> manualErrorHandler(Exception ex) {
          return new ResponseEntity<>(ex.getMessage() , HttpStatus.NOT_FOUND);
      }

}
