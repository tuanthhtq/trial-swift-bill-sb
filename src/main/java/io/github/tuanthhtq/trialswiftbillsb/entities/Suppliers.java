package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Suppliers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "supplier_id")
	private Long id;

	@Column(name = "supplier_name", nullable = false)
	private String name;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String phone;

	@OneToMany(
			fetch = FetchType.LAZY,
			mappedBy = "supplier"
	)
	private Set<Products> products;

	@CreationTimestamp
	private Instant createdDate;

	@UpdateTimestamp
	private Instant modifiedDate;

	public Suppliers(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public Suppliers(String phone, String name) {
		this.phone = phone;
		this.name = name;
	}
}
