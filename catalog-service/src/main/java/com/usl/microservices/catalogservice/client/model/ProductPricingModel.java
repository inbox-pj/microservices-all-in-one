package com.usl.microservices.catalogservice.client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ProductPricingModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pricingId;

	private String productCode;

	private BigDecimal price;

	private Integer version;

	private Date whenModified;

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
