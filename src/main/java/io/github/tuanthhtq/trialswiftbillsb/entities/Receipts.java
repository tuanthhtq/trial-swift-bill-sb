package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author io.github.tuanthhtq
 */

@Entity(name = "receipts")
@Getter
@Setter
@NoArgsConstructor
public class Receipts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


}
