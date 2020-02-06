package com.customer.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.domain.BaseTransaction;
import com.customer.domain.Transaction;
import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.DateRangeException;
import com.customer.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Transaction Rest API", description = "Operations for customer purchase transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@ApiOperation(value = "View a list of all transactions of all customers with in date range if given. Otherwise all.", response = List.class)
	@GetMapping(value = "/transactions", produces = "application/json")
	public List<Transaction> getTransactions(
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If fromDate given, toDate is required.", required = false) @RequestParam(required = false, name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If toDate given, fromDate is required.", required = false) @RequestParam(required = false, name = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) 
			throws DateRangeException, Exception {
		validateDateRange(fromDate, toDate);
		return transactionService.getTransactions(fromDate, toDate);
	}
	
	@ApiOperation(value = "View a list of all transactions of given customer with in date range if given. Otherwise all transaction of given customer", response = List.class)
	@GetMapping(value = "/transactions/{customerId}", produces = "application/json")
	public List<Transaction> getTransactionsByCustomer(
			@ApiParam(value = "Customer Id", required = true, example = "123") @PathVariable(name = "customerId", required = true) Long customerId, 
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If fromDate given, toDate is required.", required = false) @RequestParam(required = false, name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If toDate given, fromDate is required.", required = false) @RequestParam(required = false, name = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) 
			throws CustomerNotFoundException, DateRangeException, Exception {
		validateDateRange(fromDate, toDate);
		return transactionService.getTransactionsByCustomer(customerId, fromDate, toDate);
	}
	
	@ApiOperation(value = "Posting a transaction.", response = Transaction.class)
	@PostMapping(value = "/transactions", consumes = "application/json", produces = "application/json")
	public Transaction addTransaction(@RequestBody BaseTransaction baseTransaction) {
		return transactionService.addTransaction(baseTransaction);
	}
	
	private void validateDateRange(LocalDate fromDate, LocalDate toDate) throws DateRangeException {
		if ((fromDate != null && toDate == null) || (fromDate == null && toDate != null)) {
			throw new DateRangeException("FromDate/ToDate are mutually/both required if given one of them.");
		}
	}
}
