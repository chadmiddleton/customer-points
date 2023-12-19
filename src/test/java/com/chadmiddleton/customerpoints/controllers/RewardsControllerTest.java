package com.chadmiddleton.customerpoints.controllers;

import com.chadmiddleton.customerpoints.Transaction;
import com.chadmiddleton.customerpoints.services.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

//@WebMvcTest(RewardsController.class)
public class RewardsControllerTest {
    @InjectMocks
    private RewardsController rewardsController;
    @Mock
    private RewardService rewardService;
    List<Transaction> transactions = new ArrayList<>();
    Transaction transaction = new Transaction();

    @BeforeEach void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void calculateTotalRewards(){
        //Given
        transaction.setAccount(123);
        transaction.setAmount(125.00);
        transaction.setDate(Month.APRIL);
        transactions.add(transaction);
        when(rewardService.calculateRewardPointsForAccount(any())).thenCallRealMethod();
        //When
        rewardsController.calculateTotalRewardsForAccount(transactions);
        //Then
        verify(rewardService).calculateRewardPointsForAccount(transactions);
    }
    @Test
    public void calculateTotalRewardsMonthly(){
        //Given
        transaction.setAccount(123);
        transaction.setAmount(125.00);
        transaction.setDate(Month.APRIL);
        transactions.add(transaction);
        when(rewardService.calculateRewardPointsMonthly(any())).thenCallRealMethod();
        //When
        rewardsController.calculateRewardsMonthly(transactions);
        //Then
        verify(rewardService).calculateRewardPointsMonthly(transactions);
    }
}
