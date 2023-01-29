package com.example.splitwise.repositories;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpensePayingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpensePayingUserRepository extends JpaRepository<ExpensePayingUser,Long> {
    List<ExpensePayingUser> findAllByExpense(Expense expense);
}
