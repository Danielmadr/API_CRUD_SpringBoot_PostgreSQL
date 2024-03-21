package com.andrade.crud.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity treat404(){
    var exception = new ExceptionDTO("Objeto não encontrado");
    return ResponseEntity.badRequest().body(exception);
  }
}
