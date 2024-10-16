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
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	@Column(name = "product_name", nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private float price;

	@Column(nullable = false)
	private float costPrice;

	@Column(nullable = false)
	private float stock;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "measure_unit_id")
	private MeasureUnit measureUnit;

	private String barcode;

	private boolean isActive = true;

	private float reorderLevel = 1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	private ProductBrands brand;

	@OneToMany(
			mappedBy = "product",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL
	)
	private Set<Images> images;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private ProductCategories category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supplier_id")
	private Suppliers supplier;

	@CreationTimestamp
	private Instant createdDate;

	@UpdateTimestamp
	private Instant modifiedDate;

	public Products(String name, String description, float price, float costPrice, float stock, MeasureUnit measureUnit, String barcode) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.costPrice = costPrice;
		this.stock = stock;
		this.measureUnit = measureUnit;
		this.barcode = barcode;
	}

	public Products(String name, String description, float price, float costPrice, float stock, MeasureUnit measureUnit) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.costPrice = costPrice;
		this.stock = stock;
		this.measureUnit = measureUnit;
	}

	public Products(String name, String description, float price, float costPrice, float stock, MeasureUnit measureUnit, String barcode, ProductCategories category, Suppliers supplier, Set<Images> images, ProductBrands brand) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.costPrice = costPrice;
		this.stock = stock;
		this.measureUnit = measureUnit;
		this.barcode = barcode;
		this.category = category;
		this.supplier = supplier;
		this.images = images;
		this.brand = brand;
	}
}
