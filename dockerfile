# Use the official OpenJDK image as the base image
FROM openjdk:11-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Gradle build files and wrapper script
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle .
COPY settings.gradle .

# Copy the source code
COPY src ./src

# Make the Gradle wrapper executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Expose the port the app runs on
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "build/libs/calender.jar"]