package com.customer.domain;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Monthly Reward")
public class MonthlyReward implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Month and Year formatted as Month-Year")
	private String monthYear;
	
	@ApiModelProperty(notes = "Reward points for monthyear")
	private Double rewardPoints;
	
	public String getMonthYear() {
		return monthYear;
	}
	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	public Double getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(Double rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
}
