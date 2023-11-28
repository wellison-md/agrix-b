package com.betrybe.agrix.controllers;

/**
 * The type Crop not found exception.
 */
public class CropNotFoundException extends RuntimeException {

  /**
   * Instantiates a new Crop not found exception.
   */
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
