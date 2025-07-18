package com.ochwada.expense_assistant.controller;


import com.ochwada.expense_assistant.dto.ExpenseRequest;
import com.ochwada.expense_assistant.model.Expense;
import com.ochwada.expense_assistant.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * *******************************************************
 * Package: com.ochwada.expense_assistant.controller
 * File: ExpenseController.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 5:50 PM
 * Description: REST Controller for mapping HTTP's Requests' URL's with Java methods.
 * Objective:
 * *******************************************************
 */

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService service;

    @Autowired
    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping
    public Expense addExpense(@Valid @RequestBody ExpenseRequest request){
        return service.addExpense(request);

    }

    @GetMapping
    public List<Expense> getAllExpenses(){
        return  service.getAllExpense();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable String id){
        Optional<Expense> expense = service.getExpenseById(id);

        if (expense.isPresent()){
            return  ResponseEntity.ok(expense.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable String id){
        service.deleteExpense(id);

        return ResponseEntity.noContent().build();
    }


}
