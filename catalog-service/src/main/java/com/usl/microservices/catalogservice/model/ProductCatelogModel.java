package com.usl.microservices.catalogservice.model;

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
@Table(name = "PRODUCT_CATELOG")
public class ProductCatelogModel {

	@Id
	@Column(name = "PRODUCT_CATELOG_KEY", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer catelogId;

	@Column(name = "PRODUCT_CODE", nullable = false)
	private String productCode;

	@Column(name = "PRODUCT_NAME", nullable = false)
	private String productName;

	@Column(name = "PRODUCT_CATEGORY", nullable = false)
	private String productCategory;

	@Column(name = "RESERV_QUANTITY", nullable = true)
	private Integer reservQuanitity;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quanitity;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@Column(name = "WHEN_MODIFIED", nullable = false)
	private Date whenModified;

	@Transient
	private String envDescription;

	@Transient
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
