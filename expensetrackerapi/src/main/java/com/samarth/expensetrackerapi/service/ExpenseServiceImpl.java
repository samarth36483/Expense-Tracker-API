package com.samarth.expensetrackerapi.service;

import com.samarth.expensetrackerapi.exceptions.ResourceNotFoundException;
import com.samarth.expensetrackerapi.models.Expense;
import com.samarth.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        //List<Expense> list = expenseRepository.findAll();
        // since findAll() method returns List of objects
        return expenseRepository.findByUserId(userService.getLoggedInUser().getId(), page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> expense = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if(!expense.isEmpty())
            return expense.get();
        throw new ResourceNotFoundException("No expense found with id " + id);
    }

    @Override
    public String deleteById(Long id) {
        Expense expense = getExpenseById(id);
        expenseRepository.delete(expense);
        return "deleted successfully";
    }

    @Override
    public Expense saveExpense(Expense expense) {
        expense.setUser(userService.getLoggedInUser());
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

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
        return expenseRepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(), category, page).toList();
    }

    @Override
    public List<Expense> readByName(String keyword, Pageable page) {
        return expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword, page).toList();
    }

    @Override
    public List<Expense> readByDate(Date startDate, Date endDate, Pageable page) {
        if(startDate == null){
            startDate = new Date(0);
        }
        if(endDate == null){
            endDate = new Date(System.currentTimeMillis());
        }
        return expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(), startDate, endDate, page).toList();
    }
}
