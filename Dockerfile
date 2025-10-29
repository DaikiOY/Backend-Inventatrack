# Imagen base con Maven y Java
FROM maven:3.9.3-eclipse-temurin-17 AS build

WORKDIR /app

# Copiamos pom y código
COPY pom.xml .
COPY src ./src

# Compilamos el JAR
RUN mvn clean package -DskipTests

# Segunda etapa: imagen más liviana solo con Java
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiamos el JAR desde la etapa build
COPY --from=build /app/target/inventatrack-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
