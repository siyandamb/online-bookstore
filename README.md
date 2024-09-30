# Online Bookstore Application

Welcome to the Online Bookstore Application! This application allows users to place orders for books, view available books, and manage their orders through a simple RESTful API built using Spring Boot. 

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
 - [Prerequisites](#prerequisites)
 - [Installation](#installation)
 - [Configuration](#configuration)
- [API Documentation](#api-documentation)
 - [Base URL](#base-url)
 - [Endpoints](#endpoints)
 - [Place an Order](#place-an-order)
 - [View All Books](#view-all-books)
 - [Get Book by ISBN](#get-book-by-isbn)
- [Error Handling](#error-handling)
- [Running the Application](#running-the-application)
- [Testing](#testing)

## Features

- **Place an Order**: Users can place orders for books by specifying the ISBN and quantity.
- **View Books**: List of all available books with their details.
- **Get Book by ISBN**: Retrieve detailed information about a specific book using its ISBN.
- **Error Handling**: Proper error handling for various scenarios such as insufficient stock, invalid input, and book not found.
- **Unit Testing**: Comprehensive unit tests to ensure code reliability and correctness.

## Technologies Used

- **Java 21+**: Programming language used for developing the application.
- **Spring Boot**: Framework for building RESTful applications.
- **Spring Data JPA**: For database interactions and Object-Relational Mapping (ORM).
- **H2 Database**: An in-memory database for testing and development.
- **JUnit 5**: For unit testing the application.
- **Mockito**: For mocking dependencies in tests.
- **Maven**: Dependency management and build tool.

## Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- [Java JDK 21+](https://www.oracle.com/java/technologies/javase/jdk11-downloads.html)
- [Maven](https://maven.apache.org/download.cgi) (for dependency management and building the project)
- An IDE (e.g., IntelliJ IDEA, Eclipse) for code editing.

### Installation

1. **Clone the repository:**

 ```bash
 git clone https://github.com/siyandamb/online-bookstore.git
 cd online-bookstore
 ```

2. **Build the project using Maven:**

 ```bash
 mvn clean install
 ```

3. **Start the application:**

 ```bash
 mvn spring-boot:run
 ```

### Configuration

- The application uses H2 as an in-memory database, which means no additional database setup is required for development.
- You can modify the application properties in `src/main/resources/application.properties` if needed.

## API Documentation

### Base URL

```
http://localhost:8080/api
```

### Endpoints

#### Place an Order

- **URL**: `/orders`
- **Method**: `POST`
- **Parameters**:
 - `isbn` (String): The ISBN of the book.
 - `quantity` (int): The quantity of the book to order.
- **Request Body**:

 ```json
 {
 "isbn": "978-0134685991",
 "quantity": 2
 }
 ```

- **Response**:
 - `201 Created`: Order created successfully.
 - `400 Bad Request`: Invalid request (e.g., book not found, quantity is zero).

**Example Request**:

```http
POST /api/orders
Content-Type: application/json

{
 "isbn": "978-0134685991",
 "quantity": 2
}
```

**Example Response**:

```json
{
 "message": "Order placed successfully",
 "order": {
 "isbn": "978-0134685991",
 "quantity": 2,
 "totalPrice": 90.00
 }
}
```

#### View All Books

- **URL**: `/books`
- **Method**: `GET`
- **Response**: A list of all available books.

**Example Request**:

```http
GET /api/books
```

**Example Response**:

```json
[
 {
 "isbn": "978-0134685991",
 "title": "Effective Java",
 "author": "Joshua Bloch",
 "price": 45.00,
 "stock": 10
 },
 {
 "isbn": "978-0132952469",
 "title": "Clean Code",
 "author": "Robert C. Martin",
 "price": 40.00,
 "stock": 5
 }
]
```

#### Get Book by ISBN

- **URL**: `/books/{isbn}`
- **Method**: `GET`
- **Response**: Detailed information about the specified book.

**Example Request**:

```http
GET /api/books/978-0134685991
```

**Example Response**:

```json
{
 "isbn": "978-0134685991",
 "title": "Effective Java",
 "author": "Joshua Bloch",
 "price": 45.00,
 "stock": 10
}
```

### Error Handling

The API provides meaningful error messages and HTTP status codes. Common scenarios include:

- **400 Bad Request**: Returned for invalid input such as:
 - When the quantity is less than or equal to zero.
 - When the ISBN does not match any available books.
- **404 Not Found**: Returned when trying to access a book that doesn't exist.
- **500 Internal Server Error**: Returned for unexpected errors on the server side.

## Running the Application

To run the application, use the following command:

```bash
mvn spring-boot:run
```

The application will start on port 8080 by default. You can access the API using a REST client like Postman or cURL.

## Testing

To run the unit tests, use the following command:

```bash
mvn test
```

This will execute all the tests in the project, ensuring that everything is functioning as expected. The test results will be available in the console output.

