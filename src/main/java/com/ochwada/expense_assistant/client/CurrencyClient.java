package com.ochwada.expense_assistant.client;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * *******************************************************
 * Package: com.ochwada.expense_assistant.client
 * File: CurrencyClient.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 11:02 AM
 * Description:  Client to fetch data from FreeCurrency API
 * Objective:
 * *******************************************************
 */

@Component
public class CurrencyClient {
    /**
     * Spring's RestTemplate used to send HTTP requests to the currency API.
     */
    private final RestTemplate template;

    /**
     * Jackson ObjectMapper for converting JSON responses to Java objects.
     */
    private final ObjectMapper mapper;

    /**
     * The API key for accessing the FreeCurrency API. Injected from application properties.
     */
    @Value("${currency.api.key}")
    private String apiKey;

    /**
     * The base URL of the FreeCurrency API. Injected from application properties.
     */
    @Value("${currency.api.url}")
    private String apiUrl;

    /**
     * The base defaultTargetCurrency. Injected from application properties.
     */
    @Value("${app.defaultTargetCurrency}")
    private String defaultTargetCurrency;


    /**
     * Constructs a {@code CurrencyApiClient} with the given RestTemplate.
     *
     * @param template the RestTemplate used for making HTTP requests
     */
    @Autowired
    public CurrencyClient(RestTemplate template) {
        this.template = template;
        this.mapper = new ObjectMapper();
    }

/**
 * Fetch the latest exchange rate from the source currency to the target currency {@code defaultTargetCurrency} using
 * the FreeCurrency API.
 * @param amount  the amount used in the Local currency during travel in different cities
 * @param sourceCurrency the 3-letter ISO code of the source currency (e.g., "EUR") from the amount
 */
    public CurrencyData getExchangeRate(double amount, String sourceCurrency){
        // Construct the API request URL using source and target currencies
        String url = String.format(
                "%s?apikey=%s&base_currency=%s&currencies=%s",
                apiUrl,
                apiKey,
                sourceCurrency,
                defaultTargetCurrency
        );
        try {
            // Send GET request to the currency API
            String response = template.getForObject(url, String.class);

            // Parse the JSON response to extract the exchange rate
            JsonNode root = mapper.readTree(response);
            JsonNode rateNode = root.path("data").path(defaultTargetCurrency);

            // Check if the rate node exists in the response.
            if (rateNode.isMissingNode()){
                throw new RuntimeException("Currency rate not found for " + defaultTargetCurrency);
            }
            // Extract the rate as a double
            double rate = rateNode.asDouble();

            // Calculate converted amount using the exchange rate
            double converted = amount *  rate;

            // Return all information wrapped in a CurrencyData object
            return new CurrencyData(sourceCurrency, defaultTargetCurrency, amount, rate, converted);

        } catch (Exception e) {
            throw new RuntimeException( "Failed to fetch currency conversion data" + e.getMessage(), e);
        }

    }



    // ==============================CurrencyData POJO =========================================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrencyData {
        private String sourceCurrency;
        private String targetCurrency;
        private double amount;
        private double exchangeRate;
        private double convertedAmount;


    }

}
