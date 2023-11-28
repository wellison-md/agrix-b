package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dtos.CropDto;
import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.services.CropService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping
  public List<CropDto> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();

    return crops.stream()
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()))
        .collect(Collectors.toList());
  }

  /**
   * Gets crop by id.
   *
   * @param id the id
   * @return the crop by id
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropDto> getCropById(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Crop crop = optionalCrop.get();

    CropDto cropResponseDto = new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getFarm().getId(),
        crop.getPlantedDate(),
        crop.getHarvestDate()
    );

    return ResponseEntity.ok().body(cropResponseDto);
  }

  /**
   * Gets crop by harvest date.
   *
   * @param start the start
   * @param end   the end
   * @return the crop by harvest date
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> getCropByHarvestDate(
      @RequestParam("start") LocalDate start,
      @RequestParam("end") LocalDate end
  ) {
    Optional<List<Crop>> crops = cropService.getCropByHarvestDate(start, end);

    if (crops.isEmpty()) {
      throw new CropNotFoundException();
    }

    List<Crop> getCrops = crops.get();

    List<CropDto> responseCrops = getCrops.stream()
        .map((crop) -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getFarm().getId(),
            crop.getPlantedDate(),
            crop.getHarvestDate()))
        .collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(responseCrops);
  }
}
