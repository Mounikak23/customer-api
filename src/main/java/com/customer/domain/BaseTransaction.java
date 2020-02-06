package com.customer.domain;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Base transaction details for posting a new transaction")
public class BaseTransaction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "customer id")
	private Long customerId;
	
	@ApiModelProperty(notes = "transaction total amount")
	private Double transactionTotal;
	
	@ApiModelProperty(notes = "transaction date, format as yyyy-MM-dd")
	private LocalDate transactionDate;
	
	public BaseTransaction() {
	}

	public BaseTransaction(Long customerId, Double transactionTotal, LocalDate transactionDate) {
		this.customerId = customerId;
		this.transactionTotal = transactionTotal;
		this.transactionDate = transactionDate;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Double getTransactionTotal() {
		return transactionTotal;
	}

	public void setTransactionTotal(Double transactionTotal) {
		this.transactionTotal = transactionTotal;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
}

