package com.betrybe.agrix.dtos;

import com.betrybe.agrix.entities.Farm;

/**
 * The type Farm dto.
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * To farm farm.
   *
   * @return the farm
   */
//  public Farm toFarm() {
//    return new Farm(id, name, size);
//  }
}