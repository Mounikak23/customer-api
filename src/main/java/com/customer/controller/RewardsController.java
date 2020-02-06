package com.customer.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.customer.domain.Reward;
import com.customer.exception.CustomerNotFoundException;
import com.customer.exception.DateRangeException;
import com.customer.service.RewardsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value = "Rewards Rest API", description = "Operations for customer rewards")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;
	
	@ApiOperation(value = "View a list of monthwise/total rewards of all customers with in date range if given. Otherwise all.", response = List.class)
	@GetMapping(value = "/rewards", produces = "application/json")
	public List<Reward> getRewards(
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If fromDate given, toDate is required.", required = false) @RequestParam(required = false, name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, 
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If toDate given, fromDate is required.", required = false) @RequestParam(required = false, name = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) 
			throws DateRangeException, Exception {
		validateDateRange(fromDate, toDate);
		return rewardsService.getRewards(fromDate, toDate);
	}
	
	@ApiOperation(value = "View a list of monthwise/total rewards of given customer with in date range if given. Otherwise all.", response = Reward.class)
	@GetMapping(value = "/rewards/{customerId}", produces = "application/json")
	public Reward getRewardsByCustomer(
			@ApiParam(value = "Customer Id", required = true, example = "123") @PathVariable(name = "customerId", required = true) Long customerId,
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If fromDate given, toDate is required.", required = false) @RequestParam(required = false, name = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate, 
			@ApiParam(value = "Format should be yyyy-MM-dd. Its Optional. If toDate given, fromDate is required.", required = false) @RequestParam(required = false, name = "toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate) 
			throws CustomerNotFoundException, DateRangeException, Exception {
		validateDateRange(fromDate, toDate);
		return rewardsService.getRewardsByCustomer(customerId, fromDate, toDate);
	}
	
	private void validateDateRange(LocalDate fromDate, LocalDate toDate) throws DateRangeException {
		System.out.println("fromDate-" + fromDate + ":: toDate-" + toDate);
		if ((fromDate != null && toDate == null) || (fromDate == null && toDate != null)) {
			throw new DateRangeException("FromDate/ToDate are mutually/both required if given one of them.");
		}
	}
}
