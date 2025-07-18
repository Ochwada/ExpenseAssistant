 # 💸 Expense Assistant

A full-stack Java REST API built with **Spring Boot** that helps travelers and remote workers track their expenses globally. 
The app integrates real-time **currency conversion**, **live weather updates**, and **MongoDB** for data persistence—fully 
containerized with **Docker**.

## 🌍 Project Overview

**Expense Assistant** is designed to make global expense tracking effortless. Whether you're a digital nomad, tourist, 
or remote worker, this application lets you:

- Record expenses in various currencies and cities 
- Get live currency conversion to your preferred currency 
- See the weather of where you made the purchase 
- Store and manage all data in MongoDB 
- Deploy everything using Docker and Docker Compose

## Tech Stack

#### Required Spring Boot Dependencies
To run this project, make sure the following dependencies are included in your `pom.xml`:

- `spring-boot-starter-web `– For building RESTful APIs 
- `spring-boot-starter-data-mongodb` – For MongoDB integration 
- `spring-boot-starter-validation` – For validating request data 
- `lombok – (Optional) `Reduces boilerplate code for models and DTOs

#### Technologies Involved
This application uses the following tools and technologies:

- **Spring Boot + Maven** – Core backend framework and build tool 
- **RestTemplate** – To fetch data from external APIs 
- **MongoDB Atlas** – Cloud NoSQL database 
- **Docker (multistage build)** – For containerizing the application 
- **Docker Compose** – For managing environment variables and services


<br>

| Layer                | Technology                                                                                        |
|----------------------|---------------------------------------------------------------------------------------------------|
| **Backend**          | Java 17+, Spring Boot (Maven), Spring Web                                                         |
| **Validation**       | Spring Validation                                                                                 |
| **Database**         | MongoDB Atlas (NoSQL cloud DB)                                                                    |
| **External APIs**    | [Free Currency API](https://freecurrencyapi.com/), [OpenWeather API](https://openweathermap.org/) |
| **Data Access**      | Spring Data MongoDB                                                                               |
| **JSON Parsing**     | Jackson                                                                                           |
| **HTTP Clients**     | RestTemplate                                                                                      |
| **Containerization** | Docker (Multistage Build)                                                                         |
| **Orchestration**    | Docker Compose (env vars and deployment)                                                          |

## Features
1. Add an expense: amount, currency, city, description 
2. Fetch live exchange rate to convert into default/home currency 
3. Fetch current weather for the city at time of purchase 
4. Persist records in MongoDB Atlas 
5. List all expenses 
6. Search/filter expenses by city or date 
7. Delete any expense 
8. Fully containerized with Docker & Docker Compose 

## API Endpoints
| Method   | Endpoint                    | Description             |
|----------|-----------------------------|-------------------------|
| `POST`   | `/api/expenses`             | Add a new expense       |
| `GET`    | `/api/expenses`             | Get all expenses        |
| `GET`    | `/api/expenses?city=Berlin` | Filter expenses by city |
| `DELETE` | `/api/expenses/{id}`        | Delete an expense by ID |


## Example Usage
### Example User Input

```bash
{
  "amount": 150, # used amount
  "currency": "EUR", # Source Currency - used amount
  "city": "Barcelona", # Place the amount was used
  "description": "Dinner with clients." # Description of what the amount was used for
}
```
The system will:
- Get exchange rate (EUR → USD or configurable default)
- Get current weather in "Barcelona"
- Save to MongoDB:

```bash

{
  "amountOriginal": 150,
  "originalCurrency": "EUR",
  "convertedAmount": 164.25,
  "targetCurrency": "USD",
  "city": "Barcelona",
  "description": "Dinner with clients",
  "weather": "clear sky",
  "temperature": 25.0
}
```
## Project Structure
```yaml
├── ---------------------------------------------------------------------------------------------- 

expense_assistant/
├── .env                       # Environment variables (e.g., DB credentials, API keys)
├── .gitignore                 # Git ignore rules
├── expense_assistant.iml  # IntelliJ project config
├── mvnw / mvnw.cmd            # Maven Wrapper scripts
├── pom.xml                    # Maven project configuration

├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.ochwada.expense_assistant/
│   │   │       ├── controller/             # REST API controllers
│   │   │       ├── model/                  # Domain models/entities
│   │   │       │   ├── Appointment.java
│   │   │       │   ├── Doctor.java
│   │   │       │   ├── Gender.java
│   │   │       │   ├── MedicalRecord.java
│   │   │       │   ├── Patient.java
│   │   │       │   └── Status.java
│   │   │       ├── repository/             # Spring Data repositories
│   │   │       ├── service/                # Business logic layer
│   │   │       └── ExpenseAssistantApplication.java  # Main Spring Boot application class
│   │   └── resources/                      # Application resources (e.g., application.properties, static files)

│
│   ├── test/
│   │   └── java/
│   │       └── com.ochwada.expense_assistant/
│   │           └── ExpenseAssistantApplicationTests.java  # Unit and integration tests

├── ---------------------------------------------------------------------------------------------- 
```


<br>
<br>

---
#  Setup
## Dockerized Setup
### Prerequisites
- Docker 
- Docker Compose

### Steps
1. Clone the repository 
2. Add your API keys (Currency & Weather) to .env 
3. Run:
```bash
docker-compose up --build
```
Visit the app at `http://localhost:8080`

## Environment Variables (.env)
```env

SERVER_PORT=port_number

MONGODB_URI=your_mongodb_atlas_uri
MONGODB_DATABASE=expenseDB

FREECURRENCY_API_KEY=your_currency_api_key
FREECURRENCY_API_URL=https://api.freecurrencyapi.com/v1/latest

OPENWEATHER_API_KEY=your_openweather_api_key
OPENWEATHER_API_URL=https://api.openweathermap.org/data/2.5/weather
```
<br>
<br>

---
#  Possible Future Enhancements
1. Add frontend with React or Vue 
2. Authentication with JWT 
3. Export reports as PDF or Excel 
4. Categorize expenses (food, transport, etc.)
5. Daily/Monthly summaries

<br>
<br>

---
# 📜  License
This project is open-source and available under the  [MIT License](https://opensource.org/license/mit).
<br>
<br>



---
# 👩🏾‍💻 Author
Built with 💚 by Linda Ochwada – [Linkedin](https://www.linkedin.com/in/ochwada-l-66630a36/)