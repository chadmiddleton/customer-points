package com.chadmiddleton.customerpoints;

import lombok.Data;

import java.time.Month;

@Data
public class Transaction {
    public Integer account;
    public Month date;
    public Double amount;
    public Integer rewards = 0;
}
