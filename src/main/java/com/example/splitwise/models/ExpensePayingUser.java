package com.example.splitwise.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExpensePayingUser {

    private Expense expense;

    private User user;

    private double Amount;
}
