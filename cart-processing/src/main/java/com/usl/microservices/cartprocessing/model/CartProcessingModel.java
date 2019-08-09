package com.usl.microservices.cartprocessing.model;

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
@Table(name = "SHOPPING_CART")
public class CartProcessingModel {

	@Id
	@Column(name = "SHOPPING_CART_KEY", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer shoppingCartId;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "PRODUCT_CODE", nullable = false)
	private String productCode;

	@Column(name = "PRODUCT_NAME", nullable = false)
	private String productName;

	@Column(name = "product_QUANTITY", nullable = true)
	private Integer quantity;

	@Column(name = "PRODUCT_AMOUNT", nullable = false, scale = 2, precision = 9)
	private BigDecimal amount;

	@Column(name = "STATUS", nullable = false)
	private String status;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@Column(name = "WHEN_MODIFIED", nullable = false)
	private Date whenModified;

	@Transient
	private String envDescription;

	public Integer getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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