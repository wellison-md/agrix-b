package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dtos.CropDto;
import com.betrybe.agrix.dtos.DtoConverter;
import com.betrybe.agrix.dtos.FarmDto;
import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.services.FarmService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {

  private FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param newFarm the new farm
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto newFarm) {
    Farm farm = DtoConverter.dtoToModel(newFarm);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(farmService.insertFarm(newFarm.toFarm()));
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> allFarms = farmService.getAllFarms();

    return allFarms.stream()
        .map((farm) -> new FarmDto(
            farm.getId(),
            farm.getName(),
            farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    Farm response = farmService.getFarmById(id);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(DtoConverter.modelToDto(response));
  }

  /**
   * Create crop response entity.
   *
   * @param farmId  the farm id
   * @param cropDto the crop dto
   * @return the response entity
   */
  @PostMapping(value = "/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody CropDto cropDto) {
    Crop crop = farmService.createCrop(farmId, cropDto.toCrop());
    CropDto newCropDto = new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        farmId);
    return ResponseEntity.status(HttpStatus.CREATED).body(newCropDto);
  }

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   */
  @GetMapping(value = "/{farmId}/crops")
  public ResponseEntity<List<CropDto>> getCropsByFarmId(@PathVariable Long farmId) {
    List<Crop> crops = farmService.getCropsById(farmId);
    List<CropDto> cropDtoList = crops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            farmId))
        .toList();
    return ResponseEntity.ok(cropDtoList);
  }
}
