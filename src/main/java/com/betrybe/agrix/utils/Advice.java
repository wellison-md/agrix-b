package com.betrybe.agrix.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * The type Advice.
 */
@RestControllerAdvice
public class Advice {

  /**
   * Handle farm not found response entity.
   *
   * @param exceptions the exceptions
   * @return the response entity
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleFarmNotFound(RuntimeException exceptions) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }
}
