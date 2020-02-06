package com.customer.service;

import java.time.LocalDate;
import java.util.List;

import com.customer.domain.BaseTransaction;
import com.customer.domain.Transaction;
import com.customer.exception.CustomerNotFoundException;

public interface TransactionService {

	public Transaction addTransaction(BaseTransaction baseTransaction);

	public List<Transaction> getTransactions(LocalDate fromDate, LocalDate toDate);
	
	public List<Transaction> getTransactionsByCustomer(Long customerId, LocalDate fromDate, LocalDate toDate) throws CustomerNotFoundException;
	
	public boolean isCustomerExists(Long customerId);
}
