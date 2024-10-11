package io.github.tuanthhtq.trialswiftbillsb.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

/**
 * @author io.github.tuanthhtq
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Bills {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_id")
	private Long id;

	private int orderCode;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "creator_id")
	private Users creator;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "store_id")
	private StoreInfo store;

	private float totalAmount;

	@Size(max = 50)
	private String customerName;

	@Size(max = 15)
	private String customerPhone;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "payment_method_id")
	private PaymentMethods paymentMethod;

	private boolean paid = false;

	@CreationTimestamp
	private Instant creationDate;

	public Bills(int orderCode, Users creator, float totalAmount, String customerName, String customerPhone, PaymentMethods paymentMethod, boolean paid) {
		this.orderCode = orderCode;
		this.creator = creator;
		this.totalAmount = totalAmount;
		this.customerName = customerName;
		this.customerPhone = customerPhone;
		this.paymentMethod = paymentMethod;
		this.paid = paid;
	}
}
