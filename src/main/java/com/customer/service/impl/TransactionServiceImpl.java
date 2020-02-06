package com.customer.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.domain.BaseTransaction;
import com.customer.domain.Transaction;
import com.customer.exception.CustomerNotFoundException;
import com.customer.repository.TransactionRepository;
import com.customer.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
    private static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Override
	public Transaction addTransaction(BaseTransaction baseTransaction) {
		return transactionRepository.addTransaction(baseTransaction);
	}

	@Override
	public List<Transaction> getTransactions(LocalDate fromDate, LocalDate toDate) {
		logger.info("getTransactions called for parameters - fromDate/toDate - " + fromDate + "/" + toDate);
		if (fromDate != null && toDate != null) {
			return transactionRepository.getTransactions(fromDate, toDate);
		}
		return transactionRepository.getTransactions();
	}

	@Override
	public List<Transaction> getTransactionsByCustomer(Long customerId, LocalDate fromDate, LocalDate toDate) throws CustomerNotFoundException {
		logger.info("getTransactionsByCustomer called for parameters - customerId/fromDate/toDate - " + customerId + "/" + fromDate + "/" + toDate);
		if (isCustomerExists(customerId)) {
			if (fromDate != null && toDate != null) {
				return transactionRepository.getTransactionsByCustomer(customerId, fromDate, toDate);
			}
			return transactionRepository.getTransactionsByCustomer(customerId);
		} else {
			throw new CustomerNotFoundException("Customer - " + customerId + " not found.");
		}
	}

	@Override
	public boolean isCustomerExists(Long customerId) {
		long noOfTransactions = transactionRepository.getTransactions().stream().filter(o -> o.getCustomerId().equals(customerId)).count();
		return noOfTransactions > 0;
	}
}
