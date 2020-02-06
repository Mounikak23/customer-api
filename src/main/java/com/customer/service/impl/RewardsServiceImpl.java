package com.customer.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.domain.Reward;
import com.customer.domain.Transaction;
import com.customer.exception.CustomerNotFoundException;
import com.customer.helper.RewardPopulator;
import com.customer.service.RewardsService;
import com.customer.service.TransactionService;

@Service
public class RewardsServiceImpl implements RewardsService {
	
	private static final Logger logger = LogManager.getLogger(RewardsServiceImpl.class);

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private RewardPopulator rewardPopulator;
	
	@Override
	public List<Reward> getRewards(LocalDate fromDate, LocalDate toDate) {
		logger.info("getRewards called for parameters - fromDate/toDate - " + fromDate + "/" + toDate);
		List<Transaction> transactions = transactionService.getTransactions(fromDate, toDate);

		Map<Long, List<Transaction>> transactionsByCustomer = transactions.stream()
				.collect(Collectors.groupingBy(o -> o.getCustomerId()));
		
		List<Reward> rewards = transactionsByCustomer.entrySet().stream()
									.map(o -> rewardPopulator.populate(o.getKey(), o.getValue()))
									.collect(Collectors.toList());
		
		return rewards;
	}

	@Override
	public Reward getRewardsByCustomer(Long customerId, LocalDate fromDate, LocalDate toDate) throws CustomerNotFoundException {
		logger.info("getRewardsByCustomer called for parameters - customerId/fromDate/toDate - " + customerId + "/" + fromDate + "/" + toDate);
		List<Transaction> transactions = transactionService.getTransactionsByCustomer(customerId, fromDate, toDate);
		return rewardPopulator.populate(customerId, transactions);
	}
}
