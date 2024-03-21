package com.andrade.crud.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
    String errorMessage = "Entidade não encontrada: " + ex.getMessage();
    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }

}
