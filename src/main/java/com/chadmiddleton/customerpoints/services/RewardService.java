package com.chadmiddleton.customerpoints.services;

import com.chadmiddleton.customerpoints.Transaction;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class RewardService {
    public Map<Integer, Integer> calculateRewardPointsForAccount(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transaction.setRewards(calculateSingleRewardPoint(transaction));
        }
        Map<Integer, Integer> accountRewards = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getAccount,
                        Collectors.summingInt(Transaction::getRewards)));
        accountRewards.forEach((account, rewards) ->
                System.out.println("Account " + account + ": " + rewards));
        return accountRewards;
    }

    public Map<Integer, Map<Month, Integer>> calculateRewardPointsMonthly(List<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            transaction.setRewards(calculateSingleRewardPoint(transaction));
        }
        Map<Integer, Map<Month, Integer>> groupedByAccount = transactions.stream()
                .collect((Collectors.groupingBy(Transaction::getAccount,
                        Collectors.groupingBy(Transaction::getDate,
                                Collectors.summingInt(Transaction::getRewards)))));
        groupedByAccount.forEach((account, monthlyRewards) -> {
            System.out.println("Account " + account + ": ");
            monthlyRewards.forEach((month, rewards) ->
                    System.out.println(" Month: " + month + " Rewards: " + rewards));
        });
        System.out.println(groupedByAccount);
        return groupedByAccount;
    }

    public Integer calculateSingleRewardPoint(Transaction transaction) {
        if (transaction.getAmount() == null) {
            throw new IllegalArgumentException("Input value cannot be null");
        }
        Integer rewardPoints = 0;
        Integer amount = transaction.getAmount().intValue();
        if (transaction.getAmount() > 100) {
            rewardPoints = (amount - 100) * 2 + 50;
        } else if (transaction.getAmount() > 50) {
            rewardPoints = amount - 50;
        }
        return rewardPoints;
    }
}
