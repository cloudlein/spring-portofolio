# Spring Boot CV & Portfolio Management System

A production-ready Spring Boot web application designed for managing curriculum vitae (CV) and professional portfolios.

---

## Technology Stack

### Backend & Core
- **Java**: Version 21 (Eclipse Temurin JDK)
- **Framework**: Spring Boot 3
- **Web MVC**: Spring MVC for REST APIs
- **Database Access**: Spring Data JPA (Hibernate 7.x)
- **Schema Migration**: Flyway
- **Validation**: Jakarta/Bean Validation
- **Authentication**: Stateful Session-based Security with BCrypt Password Encoder
- **Utilities**: Lombok, MapStruct (DTO Mapping)
- **Monitoring**: Spring Boot Actuator (Health, Info, and Metrics endpoints)

### Native Compilation
- **AOT Compilation**: GraalVM Native Image support (via Spring Boot AOT plugins)

### Database & Storage
- **Primary Database**: PostgreSQL (configured for Supabase compatibility)
- **Object Storage**: Supabase Storage

### DevOps & Environment
- **Containerization**: Multi-stage Docker Build (JDK 21 base image)
- **Deployment Platform**: Railway / Docker hosts
- **CI/CD**: GitHub Actions
- **Local Dev Server**: Spring Boot DevTools + Docker Compose integration

---

## Configuration & Execution

### 1. Environment Variables Setup
Create a `.env` file in the root directory (based on `.env.example`):
```bash
cp .env.example .env
```
Key variables:
- `SERVER_PORT`: Port to run the application (Default: `8080`)
- `DB_HOST`, `DB_PORT`, `DB_NAME`, `DB_USERNAME`, `DB_PASSWORD`: PostgreSQL configuration parameters
- `REDIS_HOST`, `REDIS_PORT`, `REDIS_PASSWORD`: Redis cache configuration parameters
- `JWT_SECRET`, `JWT_EXPIRATION_MS`: Authentication tokens settings

### 2. Running the Application
To launch the application using active development profiles:
```bash
./gradlew bootRun
```
*Note: Dev profile is enabled by default. Docker Compose integration will automatically spin up PostgreSQL and Redis instances locally.*

### 3. Running Integration Tests
Integration tests run inside isolated containers utilizing JUnit 5 and Testcontainers:
```bash
./gradlew test
```
