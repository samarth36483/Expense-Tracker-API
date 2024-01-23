package com.samarth.expensetrackerapi.controller;

import com.samarth.expensetrackerapi.models.Expense;
import com.samarth.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @ResponseStatus(value = HttpStatus.FOUND)
    @GetMapping("/expenses")
    public List<Expense> getAllExpense(){
        List<Expense> list = expenseService.getAllExpenses();
        return list;
    }

    @ResponseStatus(value = HttpStatus.FOUND)
    @GetMapping("/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        /* above instead of @PathVariable we can also use @RequestParam if we want to pass
        parameter in the URL using query Strings */
        Expense expense = expenseService.getExpenseById(id);
        return expense;
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/expenses/{id}")
    public String deleteExpenseById(@PathVariable Long id){
        return expenseService.deleteById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/expenses")
    public Expense saveExpense(@RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/expenses/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense){
        return expenseService.updateExpense(id, expense);
    }
}
