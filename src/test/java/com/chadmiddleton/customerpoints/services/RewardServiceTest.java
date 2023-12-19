package com.chadmiddleton.customerpoints.services;

import com.chadmiddleton.customerpoints.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {
    private final RewardService rewardService = new RewardService();

    @Test
    public void calculateSingleRewardPoint_Above100() {
        //Given
        Transaction transaction = new Transaction();
        transaction.setAmount(120.00);
        //When
        Integer result = rewardService.calculateSingleRewardPoint(transaction);
        //Then
        assertEquals(90, result);
    }

    @Test
    public void calculateSingleRewardPoint_Between50And100() {
        //Given
        Transaction transaction = new Transaction();
        transaction.setAmount(90.00);
        //When
        Integer result = rewardService.calculateSingleRewardPoint(transaction);
        //Then
        assertEquals(40, result);
    }

    @Test
    public void calculateSingleRewardPoint_LessThan50() {
        //Given
        Transaction transaction = new Transaction();
        transaction.setAmount(40.00);
        //When
        Integer result = rewardService.calculateSingleRewardPoint(transaction);
        //Then
        assertEquals(0, result);
    }

    @Test
    public void calculateSingleRewardPoint_WithDecimal() {
        //Given
        Transaction transaction = new Transaction();
        transaction.setAmount(99.99);
        //When
        Integer result = rewardService.calculateSingleRewardPoint(transaction);
        //Then
        assertEquals(49, result);
    }

    @Test
    public void calculateSingleRewardPoint_Null() {
        //Given
        Transaction transaction = new Transaction();
        //When
        //Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> rewardService.calculateSingleRewardPoint(transaction));
        assertEquals("Input value cannot be null", exception.getMessage());
    }

    @Test
    public void calculateRewardPoints_ForAccount(){
        //Given
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(70.0);
        transaction1.setAccount(123);
        Transaction transaction2 = new Transaction();
        transaction2.setAmount(70.0);
        transaction2.setAccount(123);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        //When
        Map<Integer, Integer> result = rewardService.calculateRewardPointsForAccount(transactions);
        //Then
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(123, 40);
        assertEquals(expected, result);
    }

    @Test
    public void calculateRewardPoints_ForMultAccount(){
        //Given
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(70.0);
        transaction1.setAccount(123);
        Transaction transaction2 = new Transaction();
        transaction2.setAmount(70.0);
        transaction2.setAccount(123);
        Transaction transaction3 = new Transaction();
        transaction3.setAmount(70.0);
        transaction3.setAccount(100);
        Transaction transaction4 = new Transaction();
        transaction4.setAmount(900.0);
        transaction4.setAccount(199);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        //When
        Map<Integer, Integer> result = rewardService.calculateRewardPointsForAccount(transactions);
        //Then
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(123, 40);
        expected.put(100, 20);
        expected.put(199,1650);
        assertEquals(expected, result);
    }

    @Test
    public void calculateRewardPointsMonthly_ForMultAccount(){
        //Given
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(70.0);
        transaction1.setAccount(123);
        transaction1.setDate(Month.APRIL);
        Transaction transaction2 = new Transaction();
        transaction2.setAmount(70.0);
        transaction2.setAccount(123);
        transaction2.setDate(Month.APRIL);
        Transaction transaction3 = new Transaction();
        transaction3.setAmount(70.0);
        transaction3.setAccount(123);
        transaction3.setDate(Month.MARCH);
        Transaction transaction4 = new Transaction();
        transaction4.setAmount(900.0);
        transaction4.setAccount(123);
        transaction4.setDate(Month.FEBRUARY);
        Transaction transaction5 = new Transaction();
        transaction5.setAmount(125.00);
        transaction5.setAccount(567);
        transaction5.setDate(Month.APRIL);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        //When
        Map<Integer,Map<Month, Integer>> result = rewardService.calculateRewardPointsMonthly(transactions);
        //Then
        Map<Integer,Map<Month, Integer>> expected = new HashMap<>();
        Map<Month, Integer> customer567 = new HashMap<>();
        customer567.put(Month.APRIL, 100);
        Map<Month, Integer> customer123 = new HashMap<>();
        customer123.put(Month.APRIL, 40);
        customer123.put(Month.FEBRUARY, 1650);
        customer123.put(Month.MARCH, 20);
        expected.put(567,customer567);
        expected.put(123,customer123);

        assertEquals(expected, result);
    }
}
