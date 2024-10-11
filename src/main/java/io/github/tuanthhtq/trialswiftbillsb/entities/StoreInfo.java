package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;

/**
 * @author io.github.tuanthhtq
 */

@Entity(name = "store_info")
@Getter
@Setter
@NoArgsConstructor
public class StoreInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "store_id", nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", nullable = false)
	private Addresses address;

	@OneToMany(
			mappedBy = "store",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
	)
	private Set<Images> images;

	@OneToOne(mappedBy = "store")
	private Users owner;

	@OneToMany(
			mappedBy = "store",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
	private Set<Bills> bills;

	private Instant open;
	private Instant close;

	public StoreInfo(String name, Addresses address, Users owner, Instant open, Instant close) {
		this.name = name;
		this.address = address;
		this.owner = owner;
		this.open = open;
		this.close = close;
	}
}