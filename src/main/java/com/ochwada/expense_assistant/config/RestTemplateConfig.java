package com.ochwada.expense_assistant.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * *******************************************************
 * Package: com.ochwada.expense_assistant.config
 * File: RestTemplateConfig.java
 * Author: Ochwada
 * Date: Friday, 18.Jul.2025, 4:45 PM
 * Description: {@code RestTemplateConfig} is a configuration class that defines application-wide Spring beans related to
 * REST communication.
 * Objective:
 * *******************************************************
 */

/**
 * Spring {@code @Configuration} class that defines the {@link RestTemplate} bean.
 * *
 * By declaring this bean in a central place, it ensures that the same {@code RestTemplate} instance can be injected and
 * reused throughout the application for making HTTP requests.
 */

@Configuration
public class RestTemplateConfig {

    /**
     * Creates and exposes a {@link RestTemplate} bean for making HTTP requests.
     *
     * @return a default RestTemplate instance
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
