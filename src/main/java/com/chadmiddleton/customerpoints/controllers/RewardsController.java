package com.chadmiddleton.customerpoints.controllers;

import com.chadmiddleton.customerpoints.Transaction;
import com.chadmiddleton.customerpoints.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {
    private final RewardService rewardService;

    @Autowired
    public RewardsController(RewardService rewardService){
        this.rewardService = rewardService;
    }
    @PostMapping
    public ResponseEntity<Map<Integer, Integer>> calculateTotalRewardsForAccount(@RequestBody List<Transaction> transactions) {
        Map<Integer, Integer>  rewards = rewardService.calculateRewardPointsForAccount(transactions);
        return new ResponseEntity<>(rewards, HttpStatus.CREATED);
    }

    @PostMapping("/monthly")
    public ResponseEntity<Map<Integer, Map<Month, Integer>>> calculateRewardsMonthly(@RequestBody List<Transaction> transactions){
        Map<Integer, Map<Month, Integer>> monthlyRewards = rewardService.calculateRewardPointsMonthly(transactions);
        return new ResponseEntity<>(monthlyRewards, HttpStatus.CREATED);
    }
}
