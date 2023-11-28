package com.betrybe.agrix.repositories;

import com.betrybe.agrix.entities.Crop;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * The interface Crop repository.
 */
public interface CropRepository extends JpaRepository<Crop, Long> {
  Optional<List<Crop>> findAllCropByHarvestDateBetween(
      @Param("start") LocalDate start,
      @Param("end") LocalDate end
  );
}
