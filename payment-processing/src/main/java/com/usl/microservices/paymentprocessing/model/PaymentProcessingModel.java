package com.usl.microservices.paymentprocessing.model;

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
@Table(name = "PAYMENT_PROCESSING")
public class PaymentProcessingModel {

	@Id
	@Column(name = "PAYMENT_PROCESSING_KEY", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer paymentProcessingId;

	@Column(name = "PRODUCT_CODE", nullable = false)
	private String productCode;

	@Column(name = "USER_NAME", nullable = false)
	private String userName;

	@Column(name = "PAID_AMOUNT", nullable = false, precision = 9, scale = 2)
	private BigDecimal paidAmount;

	@Column(name = "PAID_DATE", nullable = false)
	private Date paidDate;

	@Column(name = "QUANTITY", nullable = false)
	private Integer quantity;

	@Column(name = "PAYMENT_STATUS", nullable = false)
	private String paymentStatus;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Integer version;

	@Column(name = "WHEN_MODIFIED", nullable = false)
	private Date whenModified;

	@Transient
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
