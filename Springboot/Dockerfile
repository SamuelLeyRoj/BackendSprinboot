# --- ETAPA 1: Construir el ejecutable (.jar) ---
# Usamos una imagen que ya tiene Maven y Java instalados
FROM maven:3.8.5-openjdk-21 AS build
WORKDIR /app
# Copiamos todos tus archivos (pom.xml, src, etc.) al contenedor
COPY . .
# Ejecutamos el comando que empaqueta todo en un archivo .jar
RUN mvn clean package -DskipTests

# --- ETAPA 2: Ejecutar la aplicación ---
# Usamos una imagen mucho más ligera que solo tiene Java (sin Maven)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Traemos el archivo .jar creado en la Etapa 1 a esta nueva imagen
COPY --from=build /app/target/*.jar app.jar
# Exponemos el puerto de Spring Boot
EXPOSE 8080
# Arrancamos la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]