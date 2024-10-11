package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

/**
 * @author io.github.tuanthhtq
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Addresses {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long id;

	@Column(nullable = false)
	private String cityOrProvince;

	@Column(nullable = false)
	private String district;

	@Column(nullable = false)
	private String ward;

	@Column(nullable = false)
	private String detailedAddress;

	@OneToOne(mappedBy = "address")
	private Users user;

	@OneToOne(mappedBy = "address")
	private StoreInfo store;

	@CreationTimestamp
	private Instant createdDate;

	@UpdateTimestamp
	private Instant updatedDate;

	public Addresses(String cityOrProvince, String district, String ward, String detailedAddress) {
		this.cityOrProvince = cityOrProvince;
		this.district = district;
		this.ward = ward;
		this.detailedAddress = detailedAddress;
	}

	public String getFullAddress() {
		return String.format(
				"%s, %s ward, %s district, %s",
				detailedAddress, ward, district, cityOrProvince
		);
	}

}
