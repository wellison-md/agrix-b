package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dtos.FertilizerCreationDto;
import com.betrybe.agrix.dtos.FertilizerResponseDto;
import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Fertilizer controller.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  /**
   * Instantiates a new Fertilizer controller.
   *
   * @param fertilizerService the fertilizer service
   */
  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Save fertilizer response entity.
   *
   * @param fertilizerCreationDto the fertilizer creation dto
   * @return the response entity
   */
  @PostMapping()
  public ResponseEntity<FertilizerResponseDto> saveFertilizer(
      @RequestBody FertilizerCreationDto fertilizerCreationDto
  ) {
    Fertilizer fertilizer = fertilizerService.saveFertilizer(fertilizerCreationDto);

    FertilizerResponseDto fertilizerResponseDto = new FertilizerResponseDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );

    return ResponseEntity.status(HttpStatus.CREATED).body(fertilizerResponseDto);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  @GetMapping()
  public ResponseEntity<List<FertilizerResponseDto>> getAllFertilizers() {
    List<Fertilizer> fertilizers = fertilizerService.getAllFertilizers();

    List<FertilizerResponseDto> fertilizerResponseDtoList = fertilizers.stream()
        .map((fertilizer) -> new FertilizerResponseDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        )).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fertilizerResponseDtoList);
  }

}

