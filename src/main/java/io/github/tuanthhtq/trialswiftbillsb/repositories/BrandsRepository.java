package io.github.tuanthhtq.trialswiftbillsb.repositories;

import io.github.tuanthhtq.trialswiftbillsb.entities.ProductBrands;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandsRepository extends JpaRepository<ProductBrands, Long> {

	Optional<ProductBrands> findByName(String brand);
}