package com.betrybe.agrix.services;

import com.betrybe.agrix.dtos.FertilizerCreationDto;
import com.betrybe.agrix.entities.Fertilizer;
import com.betrybe.agrix.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The type Fertilizer service.
 */
@Service
public class FertilizerService {
  private FertilizerRepository fertilizerRepository;


  /**
   * Instantiates a new Fertilizer service.
   *
   * @param fertilizerRepository the fertilizer repository
   */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }


  /**
   * Save fertilizer fertilizer.
   *
   * @param fertilizer the fertilizer
   * @return the fertilizer
   */
  public Fertilizer saveFertilizer(FertilizerCreationDto fertilizer) {
    Fertilizer newFertilizer = new Fertilizer();

    newFertilizer.setName(fertilizer.name());
    newFertilizer.setBrand(fertilizer.brand());
    newFertilizer.setComposition(fertilizer.composition());

    return fertilizerRepository.save(newFertilizer);
  }

  /**
   * Gets all fertilizers.
   *
   * @return the all fertilizers
   */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }

  /**
   * Gets fertilizer by id.
   *
   * @param id the id
   * @return the fertilizer by id
   */
  public Optional<Fertilizer> getFertilizerById(Long id) {
    return fertilizerRepository.findById(id);
  }
}

