package com.samarth.expensetrackerapi.service;

import com.samarth.expensetrackerapi.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    Page<Expense> getAllExpenses(Pageable page);

    Expense getExpenseById(Long id);

    String deleteById(Long id);

    Expense saveExpense(Expense expense);

    Expense updateExpense(Long id, Expense expense);

}
