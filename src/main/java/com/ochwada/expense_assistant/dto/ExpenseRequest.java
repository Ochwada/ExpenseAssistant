package com.ochwada.expense_assistant.dto;


import jakarta.validation.constraints.*;
import lombok.*;

/**
 * *******************************************************
 * Package: com.ochwada.expense_assistant.dto
 * File: ExpenseRequest.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 10:48 AM
 * Description: Data Transfer Object for creating a new expense.
 * Objective:
 * *******************************************************
 */

@Data
public class ExpenseRequest {
    /**
     * Amount spent in the original (local) currency.
     */
    @Min(1)
    private double amount;

    /** The original currency code (e.g., EUR, GBP). */
    @NotBlank
    private String currency;

    /** City where the purchase was made. */
    @NotBlank
    private String city;

    /** Short description of the expense. */
    private String description;
}
