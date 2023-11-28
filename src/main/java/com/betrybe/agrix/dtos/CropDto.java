package com.betrybe.agrix.dtos;

import com.betrybe.agrix.entities.Crop;


/**
 * The type Crop dto.
 */
public record CropDto(Long id, String name, Double plantedArea, Long farmId) {

  /**
   * To crop crop.
   *
   * @return the crop
   */
  public Crop toCrop() {
    return new Crop(id, name, plantedArea, null);
  }
}
