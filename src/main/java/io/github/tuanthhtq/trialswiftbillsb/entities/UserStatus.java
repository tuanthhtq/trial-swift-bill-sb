package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author io.github.tuanthhtq
 */

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "user_status")
public class UserStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_status_id")
	private Long id;

	@OneToOne(mappedBy = "status")
	private Users user;

	@Column(name = "status_name")
	private String name;

	public UserStatus(String name) {
		this.name = name;
	}
}
