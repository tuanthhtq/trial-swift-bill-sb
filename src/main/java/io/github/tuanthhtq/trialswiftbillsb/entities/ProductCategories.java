package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

@Entity(name = "categories")
@Getter
@Setter
@NoArgsConstructor
public class ProductCategories {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long id;

	@Column(name = "category_name")
	private String name;

	@OneToMany(
			mappedBy = "category",
			fetch = FetchType.LAZY
	)
	private Set<Products> products;

	public ProductCategories(String name) {
		this.name = name;
	}
}
