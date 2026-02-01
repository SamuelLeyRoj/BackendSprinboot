# --- ETAPA 1: Construcción ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
# Limpiamos y empaquetamos ignorando tests para evitar errores de BD
RUN mvn clean package -DskipTests

# --- ETAPA 2: Ejecución ---
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# Copiamos el jar generado (el asterisco ayuda si el nombre cambia)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]