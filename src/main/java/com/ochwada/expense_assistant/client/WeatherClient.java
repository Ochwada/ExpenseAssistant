package com.ochwada.expense_assistant.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
 * File: WeatherClient.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 11:04 AM
 * Description: Client to fetch data from OpenWeather API
 * Objective:
 * *******************************************************
 */

@Component
public class WeatherClient {
    /**
     * Spring's RestTemplate used to send HTTP requests to the weather API.
     */
    private final RestTemplate template;

    /**
     * Jackson ObjectMapper for converting JSON responses to Java objects.
     */
    private final ObjectMapper mapper;

    /**
     * The API key for accessing the OpenWeather API. Injected from application properties.
     */
    @Value("${openweather.api.key}")
    private String apiKey;

    /**
     * The base URL of the Open API. Injected from application properties.
     */
    @Value("${openweather.api.url}")
    private String apiUrl;

    /**
     * Constructs a {@code WeatherClient} with the given RestTemplate.
     *
     * @param template the RestTemplate used for making HTTP request.
     */
    @Autowired
    public WeatherClient(RestTemplate template) {
        this.template = template;
        this.mapper = new ObjectMapper();
    }

    public WeatherData getWeatherForCity(String cityName) {
        // Construct the full URL with query parameters
        String url = String.format(
                "%s?q=%s&appid=&%s&units=metic",
                apiUrl,
                cityName,
                apiKey
        );

        try {
            // Send GET request to the open weather API
            String response = template.getForObject(url, String.class);

            // Parse the JSON response to extract weather data
            JsonNode root = mapper.readTree(response);

            //  Extracts the weather description from the JSON tree.
            String description = root.path("weather").get(0).path("description").asText();

            // Extracts the temperature value from the JSON tree.
            double temperature = root.path("main").path("temp").asDouble();
            
            // Return all information wrapped in a WeatherData object
            return new WeatherData(description, temperature);

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch weather data: " + e.getMessage(), e);
        }
    }

    // ======================WeatherData POJO =====================================
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WeatherData {
        private String weather;
        private double temperature;
    }


}






















