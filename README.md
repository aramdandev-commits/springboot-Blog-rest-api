# Blog API

A RESTful Blog API built with Java and Spring Boot for managing users, blog posts, and comments.

##  Technologies

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Lombok
- Swagger / OpenAPI
- Maven

##  Features

- User management
- Create, update, retrieve, and delete blog posts
- Add, update, retrieve, and delete comments
- Pagination and sorting
- DTO-based request and response handling
- Input validation
- Global exception handling
- JPA entity relationships
- RESTful API architecture
- API documentation with Swagger / OpenAPI

##  Architecture

The project follows a layered architecture to keep the code organized, maintainable, and easy to extend.

    Controller
        ↓
    Service
        ↓
    Repository
        ↓
    Database

### Layers

- **Controller** — Handles HTTP requests and API responses.
- **Service** — Contains the application's business logic.
- **Repository** — Handles database operations using Spring Data JPA.
- **Entity** — Represents database entities and their relationships.
- **Payload** — Handles data transfer between the API and clients.
- **Exception** — Provides centralized exception handling.
- **Security** - Provide authentication services for users.

## 📂 Project Structure

    src
    └── main
        └── java
            └── com.example.blog
                ├── controller
                ├── service
                ├── repository
                ├── entity
                ├── payload
                ├── exception
                ├── security
                └── config

##  Database

The application uses **MySQL** as the relational database and **Hibernate** as the JPA implementation.

### Main Entities

- User
- Post
- Comment

The entities are mapped using JPA relationships and managed through Spring Data JPA repositories.

## 🔗 API Endpoints

### Users

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/auth/register` | Create a new user |
| PUT | `/api/users/{id}` | Update a user |
| DELETE | `/api/users/{id}` | Delete a user |

### Posts

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/posts` | Get all posts |
| GET | `/api/posts/{id}` | Get post by ID |
| POST | `/api/posts` | Create a new post |
| PUT | `/api/posts/{id}` | Update a post |
| DELETE | `/api/posts/{id}` | Delete a post |

### Comments

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/posts/{postId}/comments/{commentId}` | Get comment by ID |
| GET | `/api/posts/{postId}/comments` | Get comments for a post |
| POST | `/api/posts/{postId}/comments` | Add a comment |
| PUT | `/api/posts/{postId}/comments/{commentId}` | Update a comment |
| DELETE | `/api/posts/{postId}/comments/{commentId}` | Delete a comment |

##  API Documentation

The API is documented using **Swagger / OpenAPI**, providing an interactive interface for exploring and testing the available endpoints.

After running the application, open:

    http://localhost:8080/swagger-ui/index.html

## ⚙️ Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 17 or higher
- Maven
- MySQL

### 1. Clone the Repository

    git clone https://github.com/your-username/blog-api.git

### 2. Navigate to the Project

    cd blog-api

### 3. Create the Database

Create a MySQL database:

    CREATE DATABASE blog_db;

### 4. Configure Database Connection

Update the database configuration in:

    src/main/resources/application.properties

Example configuration:

    spring.datasource.url=jdbc:mysql://localhost:3306/blog_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

### 5. Run the Application

Using Maven:

    mvn spring-boot:run

Or run the application directly from your IDE.

The API will be available at:

    http://localhost:8080

##  Example Request

### Create a Post

    POST /api/posts
    Content-Type: application/json

Request body:

    {
        "title": "Introduction to Spring Boot",
        "content": "Spring Boot makes it easy to build production-ready Java applications."
    }

##  Key Concepts

This project demonstrates practical usage of:

- Object-Oriented Programming
- RESTful API Design
- Layered Architecture
- Dependency Injection
- Spring Boot
- Spring Data JPA
- Hibernate ORM
- Entity Relationships
- DTO Pattern
- Exception Handling
- Input Validation
- Pagination
- Sorting
- Database Integration
- API Documentation

##  Future Improvements

- Spring Security
- JWT Authentication
- Role-Based Authorization
- Unit Testing
- Integration Testing
- Docker Containerization
- CI/CD Pipeline
