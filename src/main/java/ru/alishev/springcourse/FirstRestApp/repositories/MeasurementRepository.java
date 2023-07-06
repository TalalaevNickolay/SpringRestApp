package ru.alishev.springcourse.FirstRestApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstRestApp.models.Measurement;

/**
 * @author Neil Alishev
 */
@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    Integer countByRaining(Boolean raining);
}
