# Department Management Spring Boot Application

This project is a Spring Boot-based RESTful API for managing departments within an organization. The application includes JWT token-based authentication and authorization to secure its endpoints.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Contributing](#contributing)
- [License](#license)

## Features

- **CRUD Operations**: Create, Read, Update, and Delete departments.
- **JWT Authentication**: Secure API endpoints with JWT-based authentication.
- **Exception Handling**: Custom exception handling for resource not found.

## Technologies Used

- **Java 11**
- **Spring Boot 2.x**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Hibernate / JPA**
- **H2 Database (or any other RDBMS)**

## Setup

### Prerequisites

- Java 11 or higher
- Maven 3.x or higher
- Git

### Clone the Repository

```bash
git clone https://github.com/your-username/department-management.git
cd department-management


Build the Project
API Endpoints
Department Management
POST /departments: Create a new department.
GET /departments: Retrieve a list of all departments.
GET /department/{id}: Retrieve a department by its ID.
PUT /departments/{id}: Update a department's details.
DELETE /departments/{id}: Delete a department by its ID.
GET /departments/name/{name}: Retrieve a department by its name.
Authentication
POST /authenticate: Authenticate a user and receive a JWT token.
Example Requests
Get JWT Token
bash
Copy code
curl -X POST http://localhost:8080/authenticate -H "Content-Type: application/json" -d '{"username":"user","password":"password"}'
Access Secured Endpoint with JWT
bash
Copy code
curl -X GET http://localhost:8080/departments -H "Authorization: Bearer <your-jwt-token>"
Authentication
This application uses JWT (JSON Web Tokens) to secure the API endpoints. To access the secured endpoints:

Authenticate: Send a POST request to /authenticate with valid credentials to receive a JWT token.
Authorize: Include the JWT token in the Authorization header of your requests as Bearer <your-jwt-token>.
Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.
