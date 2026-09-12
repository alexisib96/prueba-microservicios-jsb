# =============================================
# Stage 1: Build con Maven
# =============================================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia primero el pom.xml para aprovechar la caché de capas
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y compila
COPY src ./src
RUN mvn clean package -DskipTests -B

# =============================================
# Stage 2: Runtime con JRE liviano
# =============================================
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia el JAR generado en el stage anterior
COPY --from=build /app/target/*.jar app.jar

# Puerto que expone la app
EXPOSE 8082

# Entry point
ENTRYPOINT ["java", "-jar", "app.jar"]