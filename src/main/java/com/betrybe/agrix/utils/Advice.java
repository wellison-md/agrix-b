package com.betrybe.agrix.utils;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
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
  @ExceptionHandler({
      FarmNotFoundException.class,
      CropNotFoundException.class,
      FertilizerNotFoundException.class
  })
  public ResponseEntity<String> handleNotFoundException(RuntimeException exceptions) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptions.getMessage());
  }
}
