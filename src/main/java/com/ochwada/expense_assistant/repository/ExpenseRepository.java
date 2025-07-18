package com.ochwada.expense_assistant.repository;


import com.ochwada.expense_assistant.model.Expense;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * *******************************************************
 * Package: com.ochwada.expense_assistant.repository
 * File: ExpenseRepository.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 4:50 PM
 * Description: Repository interface for the conversion document. Spring Data MongoDB automatically implements the
 * interface at runtime, providing built-in CRUD methods
 * Objective:
 * *******************************************************
 */


public interface ExpenseRepository extends MongoRepository<Expense, String> {

    // MongoRepository provides out-of-the-box CRUD methods
}
