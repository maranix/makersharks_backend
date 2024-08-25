# Makersharks Supplier Management API

Makersharks Supplier Management API is a Spring Boot-based RESTful API that allows you to manage suppliers, including retrieving, creating, and filtering suppliers based on various criteria. The application is designed with best practices for security, error handling, and validation, making it a robust foundation for supplier management systems.

## Deployment

This web backend is currently deployed on an Amazon EC2 instance. You can access it at the following link:

[Base Address](http://makersharks.maranix.in/api/)

[Ping](http://makersharks.maranix.in/api/ping)

[Swagger Docs](http://makersharks.maranix.in/swagger-ui/index.html)

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
  - [Ping Endpoints](#ping-endpoints)
  - [Supplier Endpoints](#supplier-endpoints)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Error Handling](#error-handling)
- [Validation](#validation)
- [License](#license)

## Features

- **Supplier Management:** Create, retrieve, and filter suppliers based on location, nature of business, and manufacturing process.
- **Pagination:** Supports pagination for supplier listing.
- **Validation:** Input validation using Jakarta Bean Validation.
- **Error Handling:** Centralized and consistent error handling.
- **Security:** Configured with Spring Security, supporting CORS.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- **Java 21** or higher installed.
- **Gradle** installed.

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/maranix/makersharks-backend.git
   cd makersharks-backend
   ```

2. **Configure the databse:**
    Update the application.yml or application.properties file with your database connection details.

3. **Install dependencies:**

    Run the following command to install all necessary dependencies:
    ```bash
    ./gradlew build
    ```

### Running The Application

1. **Run the Application:**

   ```bash
   ./gradlew bootRun
   ```

2. **Access the API:**
    The API will be available at http://localhost:8080.

3. **Swagger UI and API Documentation:**
    Visit http://localhost:8080/swagger-ui/index.html to explore the API documentation.


### API Endpoints

#### Ping Endpoints

-   **GET /api/ping:** Pings the server.
    -   **Response:**

        ```json
        {
         "status": 200,
         "success": true,
         "data": "Pong!"
        }
        ```
        
#### Supplier Endpoints

-   **POST /api/suppliers:** Create a new supplier.
    -   **Required Request Body:**

        ```json
        {
          "company_name": "string",
          "location": "string",
          "nature_of_business": "string",
          "manufacturing_processes": "string",
          "website": "string"
        }
        ```
    -   **Response:**

        ```json
        {
          "status": 201,
          "success": true,
          "data": {
              "id": 7,
              "company_name": "some company",
              "location": "Washington D.C",
              "nature_of_business": "small_scale",
              "manufacturing_process": "casting",
              "website": ""
            }
        }
        ```

-   **GET /api/suppliers/list:** Gets list of all suppliers.
    -   **Response:**

        ```json
        {
          "status": 200,
          "success": true,
          "data": [
              {
                "id": 1,
                "company_name": "some company",
                "location": "India",
                "nature_of_business": "small_scale",
                "manufacturing_process": "casting",
                "website": ""
              },
              {
                "id": 4,
                "company_name": "some company",
                "location": "New York",
                "nature_of_business": "small_scale",
                "manufacturing_process": "casting",
                "website": ""
              }
           ]
        }
        ```

-   **POST /api/suppliers/query:** Returns a Paginated List of Suppliers based on the given filters.
    -   **Required Request Body:**

        ```json
          {
           "location": "New York",
           "nature_of_business": "small_scale",
           "manufacturing_process": "casting"
          }
        ```
    -   **Optional Path/Query Params:**
        - `page`: Page to fetch. (Default: 0)
        - `limit`: Number of items limited to a single page. (Default: 10)

    -   **Response:**

        ```json
          {
           "status": 200,
           "success": true,
           "data": {
            "content": [],
            "pageable": {
              "page_number": 0,
              "page_size": 10,
              "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
              },
              "offset": 0,
              "paged": true,
              "unpaged": false
              },
              "last": true,
              "total_elements": 0,
              "total_pages": 0,
              "size": 10,
              "number": 0,
              "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
              },
              "number_of_elements": 0,
              "first": true,
              "empty": true
            }
          }
        ```
      
### Project Structure

The project is structured as follows:

```plaintext
makersharks-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/makersharks/
│   │   │       ├── common/            # Common Classes
│   │   │       ├── config/            # Application Configs
│   │   │       ├── controller/        # REST Controllers
│   │   │       ├── dto/               # Data Transfer Objects
│   │   │       ├── enum/              # Enum Constants
│   │   │       ├── entity/            # JPA Entities
│   │   │       ├── exception/         # Exception Handling
│   │   │       ├── repository/        # JPA Repositories
│   │   │       ├── service/           # Business Logic
│   │   │       ├── utils/             # Utility Classes
│   │   │       ├── validator/         # Validator Classes
│   │   │       └── MakersharksApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties        # Application Configuration
├── build.gradle                        # Gradle Build Configuration
└── README.md                           # Project Documentation
```

### Dependencies
-   Spring Boot: The foundation of the application.

    -   Provides an opinionated framework to simplify the development process.

-   Spring Data JPA: To interact with the database using JPA repositories.

    -   Simplifies the implementation of data access layers.

-   Spring Security: To secure the application with modern security best practices.

-   Jakarta Bean Validation: For validating user inputs.

    -   Ensures data integrity and validity.

-   SpringDoc OpenAPI: To generate and document the API using Swagger.

    -   Provides a user-friendly interface to explore API endpoints.

### Error Handling

Global exception handling is provided by @RestControllerAdvice which centralizes error management:

-   **Validation Errors:** Captures and returns validation errors with specific messages.

-   **Data Integrity Violations:** Handles unique constraint violations and other database-related errors.

-   **General Errors:** Catches unexpected exceptions and returns a standardized error response.

### Validation

The application uses Jakarta Bean Validation to ensure the correctness of input data:

-    **@NotNull, @NotEmpty, @Enum:** For validating required fields.

-    **Custom Enum Validators:** Ensures enum values are valid and converts between camelCase and snake_case.

### LICENSE

This project is licensed under the MIT License - see the LICENSE file for details.
