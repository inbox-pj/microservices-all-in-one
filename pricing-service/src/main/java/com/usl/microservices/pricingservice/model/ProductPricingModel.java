package com.usl.microservices.pricingservice.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Table(name = "PRODUCT_PRICING")
public class ProductPricingModel {

	@Id
	@Column(name = "PRODUCT_PRICING_KEY", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer pricingId;

	@Column(name = "PRODUCT_CODE", nullable = false)
	private String productCode;

	@Column(name = "PRICE", nullable = false, precision = 9, scale = 2)
	private BigDecimal price;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@Column(name = "WHEN_MODIFIED", nullable = false)
	private Date whenModified;

	@Transient
	private String envDescription;

	public ProductPricingModel() {

	}

	public Integer getPricingId() {
		return pricingId;
	}

	public void setPricingId(Integer pricingId) {
		this.pricingId = pricingId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getWhenModified() {
		return whenModified;
	}

	public void setWhenModified(Date whenModified) {
		this.whenModified = whenModified;
	}

	public String getEnvDescription() {
		return envDescription;
	}

	public void setEnvDescription(String envDescription) {
		this.envDescription = envDescription;
	}

}
