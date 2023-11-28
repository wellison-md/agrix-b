package com.betrybe.agrix.repositories;

import com.betrybe.agrix.entities.Crop;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Crop repository.
 */
public interface CropRepository extends JpaRepository<Crop, Long> {

}
