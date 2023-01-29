package com.example.splitwise.services.settleup;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseOwningUser;
import com.example.splitwise.models.ExpensePayingUser;
import com.example.splitwise.models.Group;
import com.example.splitwise.repositories.ExpenseOwingUserRepository;
import com.example.splitwise.repositories.ExpensePayingUserRepository;
import com.example.splitwise.repositories.GroupRepository;
import com.example.splitwise.services.settleup.strategies.SettleUpTransactionsCalculatorStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {

    @Qualifier("GreedySettleUpStrategy")
    private SettleUpTransactionsCalculatorStrategy settleUpTransactionsCalculatorStrategy;

    @Autowired
    public SettleUpService(SettleUpTransactionsCalculatorStrategy settleUpTransactionsCalculatorStrategy)
    {
        this.settleUpTransactionsCalculatorStrategy = settleUpTransactionsCalculatorStrategy;
    }

    private GroupRepository groupRepository;
    private ExpensePayingUserRepository expensePayingUserRepository;
    private ExpenseOwingUserRepository expenseOwingUserRepository;

    public List<Transaction> settleUpGroup(Long groupId)
    {
        Optional<Group> group = groupRepository.findById(groupId);

        if(!group.isPresent())
        {
            System.out.println("No group found");
        }

        Group grp = group.get();

        List<ExpensePayingUser> expensePayingUsers = new ArrayList<>();
        List<ExpenseOwningUser> expenseOwningUsers = new ArrayList<>();

        for(Expense expense: grp.getExpenses())
        {
            expensePayingUsers.addAll(expensePayingUserRepository.findAllByExpense(expense));
            expenseOwningUsers.addAll(expenseOwingUserRepository.findAllByExpense(expense));
        }
        return settleUpTransactionsCalculatorStrategy.getTransactions(expensePayingUsers,expenseOwningUsers);
    }


}
