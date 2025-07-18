package com.ochwada.expense_assistant.model;


import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * *******************************************************
 * Package: com.ochwada.expense_assistant.model
 * File: Expense.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 10:09 AM
 * Description: Represents an expense recorded by a user while traveling, each expense is stored in real-time.
 * Objective:
 * - Capture essential details of a financial transaction
 * - Automatically fetch and store live weather and exchange rate data
 * - Persist enriched expense records in MongoDB
 * *******************************************************
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "expenses")
public class Expense {

    /**
     * Unique identifier for the expense record.
     */
    @Id
    private String id;

    /**
     * Amount spent in the original (local) currency.
     */
    private double originalAmount;// CurrencyClient

    /**
     * The currency code (e.g., USD, EUR) in which the original amount was recorded.
     */
    private String originalCurrency; // CurrencyClient

    /**
     *  Converted amount in the user's base or home currency
     */
    private double convertedAmount; // CurrencyClient

    /**
     *  The target or home currency code for conversion - i.e. defaultTargetCurrency USD.
     */
    private  String homeCurrency; // CurrencyClient

    /**
     * City where the expense occurred.
     */
    private String city; // WeatherClient

    /**
     * A short description or purpose of the expense (e.g., "Dinner", "Taxi").
     */
    private String description;

    /**
     * Current weather condition (e.g., "Cloudy", "Sunny") at the time of expense,
     * fetched from the OpenWeather API.
     */
    private String weather; // WeatherClient

    /**
     * Current temperature in degrees Celsius at the time of expense,
     * fetched from the OpenWeather API.
     */
    private double temperature; // WeatherClient

}
