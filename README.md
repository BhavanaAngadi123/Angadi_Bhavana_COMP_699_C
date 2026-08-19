# Happy Tails 🐾

## Java Spring Boot Pet Care Platform

Happy Tails is a full-stack pet care management platform built with Java 17 and Spring Boot. It brings pet owners, sitters, sellers, and community workflows into one application, covering pet profiles, sitter bookings, marketplace operations, lost-pet reporting, sightings, authentication, and REST APIs.

## Tech Stack

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL / PostgreSQL
- Maven
- HTML, CSS, JavaScript
- Spring Mail
- GitHub Actions

## Core Features

- User registration, login, logout, and BCrypt password security
- Pet profile creation, listing, and deletion
- Pet sitter management
- Booking workflows
- Marketplace products and orders
- Lost pet and sighting workflows
- Messaging domain model
- Authenticated REST APIs
- Environment-based database configuration
- MySQL and PostgreSQL/Supabase-compatible persistence
- Java CI build with GitHub Actions

## Project Structure

```text
src/main/java/com/happytails/
├── config/
├── controller/
├── model/
├── repository/
└── HappyTailsApplication.java

src/main/resources/
├── templates/
├── static/
└── application.properties

pom.xml
Procfile
.env.example
```

## Run Locally

### Requirements

- Java 17+
- Maven 3.9+
- MySQL 8+ or PostgreSQL

### Configure the database

Set environment variables such as:

```text
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/happytails
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your-password
DDL_AUTO=update
```

For PostgreSQL/Supabase, use a JDBC PostgreSQL URL instead.

### Build

```bash
mvn clean package
```

### Run

```bash
mvn spring-boot:run
```

The application starts on port `8080` by default and also respects the `PORT` environment variable in deployed environments.

## CI

GitHub Actions compiles the Spring Boot application using Java 17 and Maven on pull requests and pushes.

## Security

Credentials and secrets are supplied through environment variables. Do not commit real database passwords, email credentials, or production secrets.

## Migration Note

This repository originated as a Python/Flask academic project and was migrated to Java 17 and Spring Boot. The Java/Spring Boot application is now the primary implementation. Legacy Flask source remains in the repository only as migration history and should not be used as the runtime entry point.

## Author

**Bhavana Angadi**

Software engineering project demonstrating Java backend development, Spring Boot, relational data modeling, authentication, REST APIs, and multi-module application design.
