# Spring Boot RESTful Blog API

A secure, scalable, and robust RESTful Blog API built with **Java** and **Spring Boot** for blog posts, categories, and comments. Designed following enterprise standards and clean architecture principles.

## 🚀 Technologies & Tech Stack

```text
- Core & Language: Java 23, Object-Oriented Programming (OOP)
- Framework: Spring Boot, Spring MVC
- Security: Spring Security, JSON Web Tokens (JWT) for stateless authentication & authorization
- Data Persistence: Spring Data JPA, Hibernate ORM
- Database: MySQL
- Utilities & Validation: Lombok, Jakarta Bean Validation
- Documentation: Springdoc OpenAPI (Swagger)
- Build Tool: Maven
```

## ✨ Features

```text
- User Management & Auth: Secure user registration, login, and authentication using Spring Security and JWT.
- Blog Posts Management: Complete CRUD operations for blog posts with support for Pagination and Sorting.
- Categories & Comments: Manage categories and add/update/delete comments associated with posts using JPA entity relationships (One-to-Many / Many-to-One).
- Architecture & Design: Clean Layered Architecture, DTO Pattern for request/response decoupling, and Dependency Injection.
- Validation & Exception Handling: Robust input validation and centralized Global Exception Handling.
- API Documentation: Interactive API testing and documentation via Swagger / OpenAPI.

```

## 🏗️ Architecture

The project follows a modular layered architecture to ensure separation of concerns, maintainability, and scalability.

```text
    Controller
        ↓
     Service
        ↓
   Repository
        ↓
    Database

```

### Layers Breakdown

```text
- Controller — Exposes REST endpoints, handles HTTP requests, and returns JSON responses.
- Service — Contains the core business logic and transactional boundaries.
- Repository — Handles database interaction and data querying using Spring Data JPA.
- Entity — Defines database tables and their relational mappings.
- Payload (DTO) — Encapsulates data transfer between clients and the API.
- Exception — Manages centralized error handling across the application.
- Security — Implements authentication, JWT filters, and role-based access control.
- Config — Holds application configurations (Swagger, Security beans, etc.).

```

## 📂 Project Structure

```text
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

```

## 🗄️ Database & Entities

The application uses MySQL as the relational database managed through Hibernate ORM.

```text
Main Entities:
- User: Handles authentication, roles, and user details.
- Post: Blog post content, title, and relations.
- Category: Categorization for blog posts.
- Comment: User comments linked to specific posts.

```

## 🌐 API Endpoints

### 🔐 Auth (User) Endpoints

```text
- POST   /api/auth/signup   - Register a new user
- POST   /api/auth/register - Register a new user (Alias)
- POST   /api/auth/signin   - Authenticate user & generate JWT
- POST   /api/auth/login    - Authenticate user & generate JWT (Alias)

```

### 🗂️ Category Endpoints

```text
- POST   /api/v1/categories       - Create a new category
- GET    /api/v1/categories       - Retrieve all categories
- GET    /api/v1/categories/{id}  - Retrieve a specific category by ID
- PUT    /api/v1/categories/{id}  - Update an existing category
- DELETE /api/v1/categories/{id}  - Delete a category

```
## 📖 API Documentation (Swagger)

Once the application is running, you can explore and test all endpoints interactively through Swagger UI:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

## 💡 Key Engineering Concepts Demonstrated

```text
- RESTful API Best Practices & Versioning
- Stateless Authentication with JWT & Spring Security
- Object-Relational Mapping (ORM) & Complex Database Relationships
- Clean Code, SOLID Principles, and DTO Pattern
- Centralized Exception Handling & Input Validation
