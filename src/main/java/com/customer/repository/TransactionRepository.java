package com.customer.repository;

import java.time.LocalDate;
import java.util.List;

import com.customer.domain.BaseTransaction;
import com.customer.domain.Transaction;

public interface TransactionRepository {

	public Transaction addTransaction(BaseTransaction baseTransaction);

	public List<Transaction> getTransactions();
	
	public List<Transaction> getTransactions(LocalDate fromDate, LocalDate toDate);
	
	public List<Transaction> getTransactionsByCustomer(Long customerId);
	
	public List<Transaction> getTransactionsByCustomer(Long customerId, LocalDate fromDate, LocalDate toDate);
}
