# Happy Tails Java Migration

This branch begins the migration of Happy Tails from Python/Flask to Java 17 and Spring Boot.

## Implemented in Java

- Spring Boot application structure with Maven
- Spring MVC + Thymeleaf server-rendered pages
- Spring Data JPA persistence
- Spring Security login/logout
- BCrypt password hashing for new accounts
- User registration
- Pet profile creation, listing, and deletion
- Environment-based database configuration
- MySQL and PostgreSQL JDBC driver support
- Spring Mail dependency/configuration ready for notification migration

## Next modules to migrate

The existing Flask application still contains broader workflows that must be ported before the Python code can be removed safely:

- sitter profiles, availability, bookings, pricing and reviews
- marketplace products, cart, orders and seller workflows
- community/social features and messaging
- lost pets and sightings
- playdates
- campaigns/admin workflows
- file/storage integration and email notifications
- automated tests and production deployment configuration

The migration is intentionally isolated on this branch so the working Flask version on `main` is not broken while Java coverage is expanded.

## Run the Java version

Requirements: Java 17+, Maven 3.9+, and MySQL/PostgreSQL.

```bash
mvn spring-boot:run
```

Configuration can be supplied through environment variables:

```text
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/happytails
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password
DDL_AUTO=update
```

For Supabase PostgreSQL, use its JDBC PostgreSQL connection URL and credentials through the same Spring datasource variables.
