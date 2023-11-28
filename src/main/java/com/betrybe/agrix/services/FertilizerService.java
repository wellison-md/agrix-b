package com.betrybe.agrix.services;

import com.betrybe.agrix.dtos.FertilizerCreationDto;
import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.repositories.FertilizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service da classe Fertilizer.
 */
@Service
public class FertilizerService {
  private FertilizerRepository fertilizerRepository;

  /**
   * Construtor da classe.
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /**
   * Método de criação de um fertilizer.
   */
  public Fertilizer saveFertilizer(FertilizerCreationDto fertilizer) {
    Fertilizer newFertilizer = new Fertilizer();

    newFertilizer.setName(fertilizer.name());
    newFertilizer.setBrand(fertilizer.brand());
    newFertilizer.setComposition(fertilizer.composition());

    return fertilizerRepository.save(newFertilizer);
  }
}

