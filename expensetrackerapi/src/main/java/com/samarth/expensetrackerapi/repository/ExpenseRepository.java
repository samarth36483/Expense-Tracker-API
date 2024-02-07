package com.samarth.expensetrackerapi.repository;

import com.samarth.expensetrackerapi.models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    Page<Expense> findByUserIdAndCategory(long userId, String category, Pageable page);

    Page<Expense> findByUserIdAndNameContaining(long userId, String keyword, Pageable page);

    Page<Expense> findByUserIdAndDateBetween(long userId, Date startDate, Date endDate, Pageable page);

    Page<Expense> findByUserId(Long userId, Pageable page);

    Optional<Expense> findByUserIdAndId(Long userid, Long expenseId);
}
