package com.customer.service;

import java.time.LocalDate;
import java.util.List;

import com.customer.domain.Reward;
import com.customer.exception.CustomerNotFoundException;

public interface RewardsService {

	public List<Reward> getRewards(LocalDate fromDate, LocalDate toDate);
	
	public Reward getRewardsByCustomer(Long customerId, LocalDate fromDate, LocalDate toDate) throws CustomerNotFoundException;
}
