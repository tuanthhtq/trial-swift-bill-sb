package io.github.tuanthhtq.trialswiftbillsb.repositories;

import io.github.tuanthhtq.trialswiftbillsb.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface RolesRepository extends JpaRepository<Roles, Long> {
	@Query("select r from roles r where r.name not like 'ROLE_ADMIN'")
	Set<Roles> findDefaultRoles();

}