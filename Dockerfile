# ==============================================================================
# Multi-stage Dockerfile for Spring Boot (Eclipse Temurin JDK 21)
# ==============================================================================

# Phase 1: Build dependency layer and run package
FROM gradle:8.12-jdk21 AS builder
WORKDIR /app
COPY --chown=gradle:gradle . .
RUN gradle build -x test --no-daemon

# Phase 2: Create a minimal, secure runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Run application as a non-root user for security
RUN groupadd -g 1001 spring && useradd -u 1001 -g spring -m spring
USER spring:spring

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
