package io.github.tuanthhtq.trialswiftbillsb.repositories;

import io.github.tuanthhtq.trialswiftbillsb.entities.ProductCategories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<ProductCategories, Long> {

	Optional<ProductCategories> findByName(String name);
}