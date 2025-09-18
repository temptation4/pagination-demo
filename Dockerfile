# Use OpenJDK 21 as the base image
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR (adjust if your artifact name is different)
COPY target/*.jar paginationdemo-0.0.1-SNAPSHOT.jar

# Expose the default Spring Boot port
EXPOSE 8070

# Run the JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
