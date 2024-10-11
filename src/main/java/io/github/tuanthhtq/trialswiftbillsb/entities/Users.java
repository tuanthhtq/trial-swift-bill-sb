package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

@Entity(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class Users {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	// login information
	@Size(min = 10, max = 15)
	@Column(nullable = false, unique = true)
	private String phone;

	@Column(nullable = false)
	private String password;

	//details
	@Size(max = 20)
	@Column(unique = true)
	private String idNumber;

	@Size(max = 100)
	private String fullName;

	@Size(min = 10, max = 15)
	private String phone2;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "rel_user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private Set<Roles> roles;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private UserStatus status;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Addresses address;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "store_id")
	private StoreInfo store;

	//metadata
	@CreationTimestamp
	private Instant createdDate;

	@UpdateTimestamp
	private Instant modifiedDate;


	public Users(String phone, String password, String idNumber, String fullName, Addresses address, Set<Roles> roles) {
		this.phone = phone;
		this.password = password;
		this.idNumber = idNumber;
		this.fullName = fullName;
		this.address = address;
		this.roles = roles;
	}
}