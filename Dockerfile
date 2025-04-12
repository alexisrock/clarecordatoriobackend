# Etapa de construcción
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar los archivos necesarios para resolver dependencias
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw
COPY mvnw.cmd .

# Descargar dependencias sin compilar todo el proyecto
RUN ./mvnw dependency:go-offline

# Copiar el resto del código fuente
COPY src ./src

# Construir el JAR
RUN ./mvnw clean package -DskipTests

# Etapa final (runtime)
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copiar el JAR desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto por defecto de Spring Boot
EXPOSE 8081

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]