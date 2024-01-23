package com.samarth.expensetrackerapi.service;

import com.samarth.expensetrackerapi.models.Expense;
import com.samarth.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public List<Expense> getAllExpenses() {
        //List<Expense> list = expenseRepository.findAll();
        // since findAll() method returns List of objects
        return expenseRepository.findAll();
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if(expense != null)
            return expense.get();
        throw new RuntimeException("No expense found with id " + id);
    }

    @Override
    public String deleteById(Long id) {
        expenseRepository.deleteById(id);
        return "deleted successfully";
    }

    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Expense updateExpense(Long id, Expense expense) {
        Expense existingexpense = getExpenseById(id);
        if(expense.getName() != null) existingexpense.setName(expense.getName());
        if(expense.getAmount() != 0) existingexpense.setAmount(expense.getAmount());
        if(expense.getCategory() != null) existingexpense.setCategory(expense.getCategory());
        if(expense.getDescription() != null) existingexpense.setDescription(expense.getDescription());
        if(expense.getDate() != null) existingexpense.setDate(expense.getDate());
        return expenseRepository.save(existingexpense);
    }
}
