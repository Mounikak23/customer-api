package com.customer.repository.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.customer.domain.BaseTransaction;
import com.customer.domain.Transaction;
import com.customer.repository.TransactionRepository;
import com.customer.service.impl.RewardsServiceImpl;

/**
 *  Generally we have to use ORM framework or JDBC using getJdbcTemplate()/getHibernateTemplate() to connect 
 *  database to get the data. Here we are using dummy data in memory.
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
	
	private static final Logger logger = LogManager.getLogger(RewardsServiceImpl.class);

	private static List<Transaction> masterTransactions = new ArrayList<>();
	
	private static Random random = new Random();
	
	static {
		loadDefaultTransactions();
	}
	
	private static void loadDefaultTransactions() {

		masterTransactions.add(new Transaction(1L, 123L, 80.23, LocalDate.of(2020, 02, 04)));
		masterTransactions.add(new Transaction(2L, 123L, 150.23, LocalDate.of(2020, 01, 04)));
		masterTransactions.add(new Transaction(3L, 123L, 34.53, LocalDate.of(2020, 02, 01)));
		
		masterTransactions.add(new Transaction(4L, 456L, 90.34, LocalDate.of(2020, 02, 04)));
		masterTransactions.add(new Transaction(5L, 456L, 150.24, LocalDate.of(2020, 01, 04)));
	}
	
	@Override
	public Transaction addTransaction(BaseTransaction baseTransaction) {
		Long transactionId = random.nextLong();
		Transaction transaction = new Transaction(transactionId, baseTransaction);
		masterTransactions.add(transaction);
		return transaction;
	}
	
	@Override
	public List<Transaction> getTransactions() {
		return masterTransactions;
	}

	@Override
	public List<Transaction> getTransactions(LocalDate fromDate, LocalDate toDate) {
		return filterTransactionsByDateRange(masterTransactions, fromDate, toDate);
	}
	
	@Override
	public List<Transaction> getTransactionsByCustomer(Long customerId) {
		List<Transaction> filteredTransactions = new ArrayList<>();
		if (customerId != null) {
			filteredTransactions = masterTransactions.stream()
										.filter(o -> customerId.equals(o.getCustomerId()))
										.collect(Collectors.toList());
		}
		return filteredTransactions;
	}

	@Override
	public List<Transaction> getTransactionsByCustomer(Long customerId, LocalDate fromDate, LocalDate toDate) {
		List<Transaction> filteredTransactions = new ArrayList<>();
		if (customerId != null) {
			filteredTransactions = getTransactionsByCustomer(customerId);
			
			if (fromDate != null && toDate != null) {
				return filterTransactionsByDateRange(filteredTransactions, fromDate, toDate);
			}
		}
		return filteredTransactions;
	}
	
	private List<Transaction> filterTransactionsByDateRange(List<Transaction> transactions, LocalDate fromDate, LocalDate toDate) {
		List<Transaction> filteredTransactions = new ArrayList<>();
		if (fromDate != null && toDate != null) {
			filteredTransactions = transactions.stream()
										.filter(o -> isBetweenInclusive(fromDate, toDate, o.getTransactionDate()))
										.collect(Collectors.toList());
		}
		return filteredTransactions;
	}
	
	private boolean isBetweenInclusive(LocalDate fromDate, LocalDate toDate, LocalDate targetDate) {
		return !targetDate.isBefore(fromDate) && !targetDate.isAfter(toDate);
	}
}
