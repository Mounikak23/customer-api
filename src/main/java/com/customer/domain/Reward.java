package com.customer.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Reward")
public class Reward implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(notes = "customer id")
	private Long customerId;
	
	@ApiModelProperty(notes = "total reward points of the customer")
	private Double totalRewardPoints;
	
	@ApiModelProperty(notes = "monthwise rewards details of the customer")
	List<MonthlyReward> monthlyRewards;

	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Double getTotalRewardPoints() {
		return totalRewardPoints;
	}
	public void setTotalRewardPoints(Double totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}
	public List<MonthlyReward> getMonthlyRewards() {
		return monthlyRewards;
	}
	public void setMonthlyRewards(List<MonthlyReward> monthlyRewards) {
		this.monthlyRewards = monthlyRewards;
	}
}
