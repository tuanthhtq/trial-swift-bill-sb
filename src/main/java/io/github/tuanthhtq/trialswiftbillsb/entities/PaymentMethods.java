package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

@Entity
@Getter
@NoArgsConstructor
public class PaymentMethods {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_method_id")
	private Long id;

	@Column(name = "payment_method_name")
	private String name;

	public PaymentMethods(String name) {
		this.name = name;
	}

	@OneToMany(
			mappedBy = "paymentMethod",
			fetch = FetchType.LAZY
	)
	private Set<Bills> bills;
}
