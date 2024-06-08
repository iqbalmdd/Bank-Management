# Bank Management System
Bank Management System is a Java-based application that handles account transactions such as deposits, withdrawals, and payments. The system ensures that account balances are maintained correctly and never go below zero, with both database constraints and application-level checks in place.

## Features

- **Account Management**: Create, retrieve, and manage accounts.
- **Transaction Handling**: Perform transactions including deposits, withdrawals, and payments.
- **Balance Validation**: Ensure account balance never goes below zero using application logic and database constraints.
- **Transaction History**: Maintain a history of all transactions performed on an account.

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building the application.
- **Spring Data JPA**: For database interactions.
- **H2 Database**: In-memory database for development and testing.
- **Maven**: For project management and dependencies.

## Project Structure

- **Controller**: Responsible for handling HTTP requests and mapping them to the appropriate service layer methods.
- **Servcie**: Contains the business logic of the application. It processes data received from the controllers and interacts with the repositories to perform CRUD operations.
- **Repository**: Responsible for data access and persistence. It provides CRUD operations by interacting with the database.
- **DTO**: Used to transfer data between different layers of the application, particularly between the client and server.
- **Entity**:  Core classes that represent the data in the application. Each entity corresponds to a table in the database
- **Constant**: Contains classes and interfaces that define fixed values used throughout the application. These include enumerations (ENUMs), table names, API URLs, and other constants that remain unchanged during the execution of the application
- **Exception**: Custom exceptions used to handle and represent errors that occur during the execution of the application. Custom exceptions provide more meaningful error messages and handling strategies.
