package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

@Entity(name = "brands")
@Getter
@Setter
@NoArgsConstructor
public class ProductBrands {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "brand_name", unique = true)
	private String name;

	@OneToMany(
			mappedBy = "brand",
			fetch = FetchType.LAZY
	)
	private Set<Products> products;

	public ProductBrands(String name) {
		this.name = name;
	}
}
