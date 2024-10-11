package io.github.tuanthhtq.trialswiftbillsb.repositories;

import io.github.tuanthhtq.trialswiftbillsb.entities.Bills;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillsRepository extends JpaRepository<Bills, Long> {


	Page<Bills> findByCreator_FullNameContains(String fullName, Pageable pageable);

	Page<Bills> findByCreator_PhoneContains(String phone, Pageable pageable);

	Page<Bills> findByCustomerNameContains(String name, Pageable pageable);

	Page<Bills> findByCustomerPhoneContains(String phone, Pageable pageable);


}