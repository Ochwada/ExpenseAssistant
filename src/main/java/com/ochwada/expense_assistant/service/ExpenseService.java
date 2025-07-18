package com.ochwada.expense_assistant.service;


import com.ochwada.expense_assistant.client.CurrencyClient;
import com.ochwada.expense_assistant.client.WeatherClient;
import com.ochwada.expense_assistant.dto.ExpenseRequest;
import com.ochwada.expense_assistant.model.Expense;
import com.ochwada.expense_assistant.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * *******************************************************
 * Package: com.ochwada.expense_assistant.service
 * File: ExpenseService.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 4:48 PM
 * Description: {@code ExpenseService} is the service layer for expense operations. This class contains the core business
 * logic
 * Objective:
 * *******************************************************
 */

@Service
public class ExpenseService {


    /**
     * Repository used to interact with the MongoDB "expenses" collection.
     */
    private final ExpenseRepository repository;

    /**
     * API client used to fetch exchange rates from the external currency API.
     */
    private final CurrencyClient currencyClient;

    /**
     * API client used to fetch exchange rates from the external weather API.
     */
    private final WeatherClient weatherClient;

    /**
     * ExpenseService} with required dependencies.
     *
     * @param repository     the {@link ExpenseRepository} used for database operations
     * @param currencyClient the {@link CurrencyClient} used to retrieve exchange rates
     * @param weatherClient  the {@link WeatherClient} used to retrieve the weather data.
     */
    public ExpenseService(ExpenseRepository repository, CurrencyClient currencyClient, WeatherClient weatherClient) {
        this.repository = repository; // Data Access Object.
        this.currencyClient = currencyClient;  // External API Client.
        this.weatherClient = weatherClient;   // External API Client.
    }

    /** -------------------------------------------------------------------------------------
     * Add business logic methods here, such as:
     * 1. addExpense()
     * 2. getAllExpense()
     * 3. getExpenseById()
     * 4. deleteExpense()
     ----------------------------------------------------------------------------------------*/
    /**
     * Saves an expense record to MongoDB "expense" collection.
     *
     * @param request the {@link ExpenseRequest} DTO to be saved.
     * @return the saved {@link Expense} object.
     * @throws RuntimeException if the exchange rate could not be fetched or the save operation fails
     */
    public Expense addExpense(ExpenseRequest request) {
        // Fetch current data
        CurrencyClient.CurrencyData currencyData = currencyClient.getExchangeRate(
                request.getAmount(),
                request.getCurrency()
        );
        // Fetch weather data
        WeatherClient.WeatherData weatherData = weatherClient.getWeatherForCity(
                request.getCity()
        );
        // Build the expense object
        Expense expense = new Expense();
        // -- currency injection
        expense.setOriginalAmount(currencyData.getAmount());
        expense.setOriginalCurrency(currencyData.getSourceCurrency());
        expense.setConvertedAmount(currencyData.getConvertedAmount());
        expense.setHomeCurrency(currencyData.getTargetCurrency());
        // -- dto injection
        expense.setCity(request.getCity());
        expense.setDescription(request.getDescription());
        // -- weather injection
        expense.setWeather(weatherData.getWeather());
        expense.setTemperature(weatherData.getTemperature());


        return repository.save(expense);
    }

    /**
     * Retrieves all expense records from the MongoDB "expenses" collection.
     *
     * @return a {@link List} of all {@link Expense} records stored in the database
     */
    public List<Expense> getAllExpense() {
        return repository.findAll();
    }

    /**
     * Retrieves a specific expense record by its unique MongoDB ID.
     *
     * @param id the ID of the expense record to retrieve
     * @return an {@link Optional} containing the {@link Expense} if found, or empty if not found
     */
    public Optional<Expense> getExpenseById(String id) {
        return repository.findById(id);
    }

    /**
     * Deletes an expense record from the MongoDB "expenses" collection by its ID.
     *
     * @param id the ID of the expense record to delete
     */
    public void deleteExpense(String id) {
        repository.deleteById(id);
    }


}
