package com.ochwada.expense_assistant;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseAssistantApplication.class, args);
	}

	//=================================== .env SETTINGS ======================================================
	static {
		// Load environment variables from .env (ignore if .env is missing, e.g., on Heroku)
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();

		// List of environment variables
		String[] envVars = {
				"MONGODB_URI",
				"FREECURRENCY_API_KEY",
				"OPENWEATHER_API_KEY"

		};

		// Iterate through each variable and set it if present
		for (String key : envVars){

			String value = dotenv.get(key);

			if (value != null){
				System.setProperty(key, value);
				System.out.println("✅ " + key + " loaded and set.");
			} else {
				System.out.println("⚠\uFE0F" + key + " not found in .env file. Skipping System.setProperty.");
			}
		}

	}

}
