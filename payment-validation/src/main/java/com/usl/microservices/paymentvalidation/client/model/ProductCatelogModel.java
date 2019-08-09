package com.usl.microservices.paymentvalidation.client.model;

import java.math.BigDecimal;
import java.util.Date;

public class ProductCatelogModel {

	private Integer catelogId;

	private String productCode;

	private String productName;

	private String productCategory;

	private Integer reservQuanitity;

	private Integer quanitity;

	private Integer version;

	private Date whenModified;

	private String envDescription;

	private BigDecimal price;

	public ProductCatelogModel() {

	}

	public Integer getCatelogId() {
		return catelogId;
	}

	public void setCatelogId(Integer catelogId) {
		this.catelogId = catelogId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Integer getReservQuanitity() {
		return reservQuanitity;
	}

	public void setReservQuanitity(Integer reservQuanitity) {
		this.reservQuanitity = reservQuanitity;
	}

	public Integer getQuanitity() {
		return quanitity;
	}

	public void setQuanitity(Integer quanitity) {
		this.quanitity = quanitity;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
