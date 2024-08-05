# Stage 1: Build the application
FROM gradle:7.5-jdk11 as builder

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files and wrapper script
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src ./src

# Build the application
RUN ./gradlew clean build --no-daemon

# Stage 2: Run the application
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/calender.jar /app/calender.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "/app/calender.jar"]