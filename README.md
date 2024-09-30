# Online Bookstore API

## Description

This is a simple online bookstore application built using Spring Boot and **Java 21+**. The application exposes a REST API for managing books and orders. It supports functionalities like looking up books, publishing new books, and placing orders.

## Table of Contents

- [Features](hashtag#features)
- [Technologies Used](hashtag#technologies-used)
- [Getting Started](hashtag#getting-started)
- [API Endpoints](hashtag#api-endpoints)
- [Database Setup](hashtag#database-setup)
- [Running the Application](hashtag#running-the-application)
- [Testing the API](hashtag#testing-the-api)
- [Contributing](hashtag#contributing)
- [License](hashtag#license)

## Features

- Look up books by ISBN.
- Publish a new book.
- Place orders for books with quantity.
- Response format in JSON or XML.

## Technologies Used

- **Java 21+**
- **Spring Boot** (Web, Data JPA)
- **H2 Database** (in-memory)
- **Lombok** (for boilerplate reduction)
- **Maven** (for project management)

## Getting Started

### Prerequisites

- Java 21+
- Maven
- IntelliJ IDEA (or any IDE of your choice)

### Cloning the Repository

Clone this repository to your local machine using:

```bash
git clone https://github.com/yourusername/your-repository-name.git
```

## API Endpoints

### Books

- **Get All Books**

 ```
 GET /api/books
 ```

- **Get Book by ISBN**

 ```
 GET /api/books/{isbn}
 ```

- **Add New Book**

 ```
 POST /api/books
 ```

 **Request Body:**
 ```json
 {
 "isbn": "1234567890",
 "title": "The Great Gatsby",
 "author": "F. Scott Fitzgerald",
 "price": 19.99,
 "stock": 10
 }
 ```

### Orders

- **Place an Order**

 ```
 POST /api/orders?isbn={isbn}&quantity={quantity}
 ```

## Database Setup

The application uses an in-memory H2 database. You can access the H2 console at:

```
http://localhost:8080/h2-console
```

### H2 Console Configuration

- **JDBC URL**: `jdbc:h2:mem:bookstoredb`
- **User Name**: `sa`
- **Password**: `password`

## Running the Application

1. Open the project in IntelliJ IDEA.
2. Ensure you have the required dependencies and configurations.
3. Run the application by executing the `main` method in `BookstoreApplication.java`.

The application will start on `http://localhost:8080`.

## Testing the API

You can test the API using **Postman** or **curl**.

### Example Requests

- To publish a new book:

 ```bash
 curl -X POST http://localhost:8080/api/books -H "Content-Type: application/json" -d '{
 "isbn": "1234567890",
 "title": "The Great Gatsby",
 "author": "F. Scott Fitzgerald",
 "price": 19.99,
 "stock": 10
 }'
 ```

- To place an order:

 ```bash
 curl -X POST "http://localhost:8080/api/orders?isbn=1234567890&quantity=2"
 ```
