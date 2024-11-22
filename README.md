# Veterinary Management System

This is a Spring Boot application designed for managing pet owners and their pets in a veterinary clinic. The system allows you to manage both owners and their pets, with various endpoints exposed for CRUD operations.

## Features

- **Manage Pet Owners**: CRUD operations for adding, updating, deleting, and viewing pet owners.
- **Manage Pets**: CRUD operations for managing pets, linking them to owners.
- **Swagger Documentation**: API endpoints are documented using Swagger for easy testing and exploration.
- **H2 Database**: An H2 database is used for storing the data, with initial data preloaded using a `data.sql` file.
- **JUnit Unit Tests**: Unit tests for core business logic are included using JUnit.

## Technologies

- **Java**: Version 11 or higher
- **Spring Boot**: For building the application
- **H2 Database**: In-memory database for quick data storage during development
- **JUnit**: For unit testing
- **Swagger**: For API documentation

## Prerequisites

Before running the application, ensure that you have the following installed:

- [Java 11 or higher](https://openjdk.java.net/)
- [Maven](https://maven.apache.org/)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Carlamarchan/VetApp.git
2. Navigate into the project directory:
   ```bash 
   cd VetApp
3. Build the application using Maven:
   ```bash 
   mvn clean install
## Running the Application
To start the application, run the following Maven command:
   ```bash
  mvn spring-boot:run
  ```

This will start the Spring Boot application on http://localhost:8080.

## Swagger UI
Once the application is running, you can access the Swagger UI to explore the available API endpoints:

http://localhost:8080/swagger-ui/index.html

## Database
The application uses an H2 in-memory database, and it comes with some sample data preloaded via the ```data.sql``` file. This allows for quick setup and testing.
