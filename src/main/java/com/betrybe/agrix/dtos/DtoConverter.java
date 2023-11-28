package com.betrybe.agrix.dtos;

import com.betrybe.agrix.entities.Farm;

/**
 * The type Dto converter.
 */
public class DtoConverter {

  /**
   * Model to dto farm dto.
   *
   * @param farm the farm
   * @return the farm dto
   */
  public static FarmDto modelToDto(Farm farm) {
    return new FarmDto(farm.getId(), farm.getName(), farm.getSize());
  }

  /**
   * Dto to model farm.
   *
   * @param dto the dto
   * @return the farm
   */
  public static Farm dtoToModel(FarmDto dto) {
    Farm farm = new Farm();

    farm.setId(dto.id());
    farm.setName(dto.name());
    farm.setSize(dto.size());

    return farm;
  }
}
