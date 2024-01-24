package com.samarth.expensetrackerapi.controller;

import com.samarth.expensetrackerapi.models.Expense;
import com.samarth.expensetrackerapi.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @ResponseStatus(value = HttpStatus.FOUND)
    @GetMapping("")
    public List<Expense> getAllExpense(Pageable page){
        List<Expense> list = expenseService.getAllExpenses(page).toList();
        return list;
    }

    @ResponseStatus(value = HttpStatus.FOUND)
    @GetMapping("/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        /* above instead of @PathVariable we can also use @RequestParam if we want to pass
        parameter in the URL using query Strings */
        Expense expense = expenseService.getExpenseById(id);
        return expense;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping("/{id}")
    public String deleteExpenseById(@PathVariable Long id){
        return expenseService.deleteById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("")
    public Expense saveExpense(@Valid @RequestBody Expense expense){
        return expenseService.saveExpense(expense);
    }

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense){
        return expenseService.updateExpense(id, expense);
    }

    @GetMapping("/category")
    public List<Expense> getBycategory(@RequestParam String category, Pageable page){
        return expenseService.readByCategory(category, page);
    }

    @GetMapping("/name")
    public List<Expense> getByName(@RequestParam String keyword, Pageable page){
        return expenseService.readByName(keyword, page);
    }

    @GetMapping("/date")
    public List<Expense> getByDate(
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            Pageable page){
        return expenseService.readByDate(startDate, endDate, page);
    }
}
