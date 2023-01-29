package com.example.splitwise.repositories;

import com.example.splitwise.models.Expense;
import com.example.splitwise.models.ExpenseOwningUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseOwingUserRepository extends JpaRepository<ExpenseOwningUser,Long> {
    List<ExpenseOwningUser> findAllByExpense(Expense expense);
}
