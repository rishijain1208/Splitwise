package com.example.splitwise.services.settleup.strategies;
import com.example.splitwise.models.ExpenseOwningUser;
import com.example.splitwise.models.ExpensePayingUser;
import com.example.splitwise.models.User;
import com.example.splitwise.services.settleup.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Qualifier("GreedySettleUpStrategy")
public class GreedySettleUpExpenseStrategy implements SettleUpTransactionsCalculatorStrategy{

    class Record
    {
        User user;
        double pendingAmount;

        Record(User user,double pendingAmount)
        {
            this.user = user;
            this.pendingAmount = pendingAmount;
        }
    }

    @Override
    public List<Transaction> getTransactions(List<ExpensePayingUser> expensePayingUsers, List<ExpenseOwningUser> expenseOwningUsers) {

        Map<User,Double> extraMoney = new HashMap<>();

        for(ExpensePayingUser user: expensePayingUsers)
        {
            if(!extraMoney.containsKey(user.getUser()))
            {
                extraMoney.put(user.getUser(),0.0);
            }
            extraMoney.put(user.getUser(),extraMoney.get(user) + user.getExpense().getAmount());
        }

        for(ExpenseOwningUser user: expenseOwningUsers)
        {
            if(!extraMoney.containsKey(user.getUser()))
            {
                extraMoney.put(user.getUser(),0.0);
            }
            extraMoney.put(user.getUser(),extraMoney.get(user) - user.getExpense().getAmount());
        }

        Queue<Record> negativeQueue = new ArrayDeque<>();
        Queue<Record> positiveQueue = new ArrayDeque<>();

        for(User user: extraMoney.keySet())
        {
            if(extraMoney.get(user) < 0)
            {
                negativeQueue.add(new Record(user,extraMoney.get(user)));
            }
            else if(extraMoney.get(user) > 0)
            {
                positiveQueue.add(new Record(user,extraMoney.get(user)));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while(!positiveQueue.isEmpty() && !negativeQueue.isEmpty())
        {
            Record firstNegative = negativeQueue.remove();
            Record firstPositive = positiveQueue.remove();

            if(firstPositive.pendingAmount > Math.abs(firstNegative.pendingAmount)) {
                transactions.add(new Transaction(firstNegative.user, firstPositive.user, Math.abs(firstNegative.pendingAmount)));

                positiveQueue.add(new Record(firstPositive.user, firstPositive.pendingAmount - Math.abs(firstNegative.pendingAmount)));
            }
            else
            {
                transactions.add(
                        new Transaction(firstNegative.user, firstPositive.user, firstPositive.pendingAmount)
                );
                negativeQueue.add(new Record(firstNegative.user, firstNegative.pendingAmount + firstPositive.pendingAmount));
            }
        }
        return transactions;
    }
}
