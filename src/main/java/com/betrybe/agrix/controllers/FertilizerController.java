package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dtos.FertilizerCreationDto;
import com.betrybe.agrix.dtos.FertilizerResponseDto;
import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller da classe Fertilizer.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerController {
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Rota POST /fertilizers.
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
}

