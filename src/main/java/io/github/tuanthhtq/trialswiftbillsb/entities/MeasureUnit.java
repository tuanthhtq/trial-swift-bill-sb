package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MeasureUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "measure_unit")
	private Long id;

	@Column(name = "measure_unit_name")
	private String name;

	@OneToMany(
			mappedBy = "measureUnit",
			fetch = FetchType.LAZY
	)
	private Set<Products> products;
}
