package com.customer.helper;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.customer.domain.MonthlyReward;
import com.customer.domain.Reward;
import com.customer.domain.Transaction;

@Component
public class RewardPopulator {
	
	public Reward populate(Long customerId, List<Transaction> transactions) {
		Reward reward = new Reward();
		reward.setCustomerId(customerId);
		
		if (!CollectionUtils.isEmpty(transactions)) {
			Map<String, List<Transaction>> transactionsByMonthYear = transactions.stream()
				.collect(Collectors.groupingBy(o -> o.getTransactionDate().getMonth().getDisplayName(TextStyle.FULL, Locale.US) + "-" + o.getTransactionDate().getYear()));
			
			List<MonthlyReward> monthlyRewards = transactionsByMonthYear.entrySet().stream().map(o -> {
				MonthlyReward monthlyReward = new MonthlyReward();
				monthlyReward.setMonthYear(o.getKey());
				monthlyReward.setRewardPoints(o.getValue().stream().collect(Collectors.summingDouble(x -> x.getRewardPointsEarned())));
				return monthlyReward;
			}).collect(Collectors.toList());
			
			reward.setMonthlyRewards(monthlyRewards);
			
			reward.setTotalRewardPoints(monthlyRewards.stream().collect(Collectors.summingDouble(x -> x.getRewardPoints())));
		}
		
		return reward;
	}
}
