package com.usl.microservices.paymentvalidation.model;

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
@Table(name = "PAYMENT_VALIDATION")
public class PaymentValidationModel {

	@Id
	@Column(name = "PAYMENT_VALIDATION_KEY", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentValidationId;

	@Column(name = "PRODUCT_CODE", nullable = false)
	private String productCode;

	@Column(name = "PRODUCT_AMOUNT", nullable = false, precision = 9, scale = 2)
	private BigDecimal productAmount;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "STATUS", nullable = false)
	private String status;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@Column(name = "WHEN_MODIFIED", nullable = false)
	private Date whenModified;

	@Transient
	private String envDescription;

	public PaymentValidationModel() {

	}

	public Integer getPaymentValidationId() {
		return paymentValidationId;
	}

	public void setPaymentValidationId(Integer paymentValidationId) {
		this.paymentValidationId = paymentValidationId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public PaymentValidationModel withProductCode(String productCode) {
		setProductCode(productCode);
		return this;
	}

	public PaymentValidationModel withProductAmount(BigDecimal productAmount) {
		setProductAmount(productAmount);
		return this;
	}

	public PaymentValidationModel withUserName(String userName) {
		setUserName(userName);
		return this;
	}

	public PaymentValidationModel withStatus(String status) {
		setStatus(status);
		return this;
	}

	public PaymentValidationModel withWhenModified(Date whenModified) {
		setWhenModified(whenModified);
		return this;
	}

	public PaymentValidationModel withEnvDescription(String envDescription) {
		setEnvDescription(envDescription);
		return this;
	}

}
