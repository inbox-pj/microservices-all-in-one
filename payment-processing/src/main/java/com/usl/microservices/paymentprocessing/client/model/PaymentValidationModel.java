package com.usl.microservices.paymentprocessing.client.model;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentValidationModel {

	private Integer paymentValidationId;

	private String productCode;

	private BigDecimal productAmount;

	private String userName;

	private String status;

	private Integer version;

	private Date whenModified;

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
