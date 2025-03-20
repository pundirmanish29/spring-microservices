# Use a lightweight OpenJDK 17 image based on Alpine Linux.
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container to /app.
WORKDIR /app

# Copy the JAR file from your target directory into the container as "app.jar".
# Ensure the JAR file name exactly matches what Maven produced.
COPY target/user-service-1.0.0-SNAPSHOT.jar app.jar

# Expose port 8080 to allow access to the application.
EXPOSE 8080

# Define the command to run the application.
ENTRYPOINT ["java", "-jar", "app.jar"]
