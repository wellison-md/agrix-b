package com.betrybe.agrix.services;

import com.betrybe.agrix.dtos.FarmCreationDto;
import com.betrybe.agrix.entities.Crop;
import com.betrybe.agrix.entities.Farm;
import com.betrybe.agrix.exceptions.FarmNotFoundException;
import com.betrybe.agrix.repositories.CropRepository;
import com.betrybe.agrix.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private FarmRepository farmRepository;
  private CropRepository cropRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   * @param cropRepository the crop repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository, CropRepository cropRepository) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Insert farm entity farm.
   *
   * @param farm the farm
   * @return the entity farm
   */
  public Farm insertFarm(FarmCreationDto farm) {
    Farm newFarm = new Farm();

    newFarm.setName(farm.name());
    newFarm.setSize(farm.size());
    return farmRepository.save(newFarm);
  }

  /**
   * Gets all farms.
   *
   * @return the all farms
   */
  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * Gets farm by id.
   *
   * @param id the id
   * @return the farm by id
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Farm getFarmById(Long id) {
    Optional<Farm> farm = farmRepository.findById(id);

    if (farm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    return farm.get();
  }

  /**
   * Create crop crop.
   *
   * @param id   the id
   * @param crop the crop
   * @return the crop
   */
  public Crop createCrop(Long id, Crop crop) {
    Farm farm = this.getFarmById(id);
    crop.setFarm(farm);

    Crop newCrop = cropRepository.save(crop);
    farm.getCrops().add(newCrop);

    crop.setFarm(farm);

    return newCrop;
  }

  /**
   * Gets crops by id.
   *
   * @param farmId the farm id
   * @return the crops by id
   */
  public List<Crop> getCropsById(Long farmId) {
    Farm farm = this.getFarmById(farmId);
    return farm.getCrops();
  }

}
