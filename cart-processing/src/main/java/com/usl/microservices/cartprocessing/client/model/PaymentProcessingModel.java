package com.usl.microservices.cartprocessing.client.model;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentProcessingModel {

	private Integer paymentProcessingId;

	private String productCode;

	private String userName;

	private BigDecimal paidAmount;

	private Date paidDate;

	private Integer quantity;

	private String paymentStatus;

	private Integer version;

	private Date whenModified;

	private String envDescription;

	public PaymentProcessingModel() {

	}

	public Integer getPaymentProcessingId() {
		return paymentProcessingId;
	}

	public void setPaymentProcessingId(Integer paymentProcessingId) {
		this.paymentProcessingId = paymentProcessingId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
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

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
