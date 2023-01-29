package com.example.splitwise.services.settleup.strategies;

import com.example.splitwise.models.ExpenseOwningUser;
import com.example.splitwise.models.ExpensePayingUser;
import com.example.splitwise.services.settleup.Transaction;

import java.util.List;

public interface SettleUpTransactionsCalculatorStrategy {

    List<Transaction> getTransactions(List<ExpensePayingUser> expensePayingUsers,List<ExpenseOwningUser> expenseOwningUsers);
}
