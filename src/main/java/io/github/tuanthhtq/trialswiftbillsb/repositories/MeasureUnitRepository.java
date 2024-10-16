package io.github.tuanthhtq.trialswiftbillsb.repositories;

import io.github.tuanthhtq.trialswiftbillsb.entities.MeasureUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeasureUnitRepository extends JpaRepository<MeasureUnit, Long> {
	Optional<MeasureUnit> findByName(String name);
}