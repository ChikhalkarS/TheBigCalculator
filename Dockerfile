# Use a base image with your system complatibility (this is for m2AIR)
FROM arm64v8/openjdk:21-jdk-slim

# Set a label for the Docker image
LABEL maintainer="ByteClub-CalulatorApp"

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/BigCalculator-0.0.1-SNAPSHOT.jar /app/BigCalculator-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "BigCalculator-0.0.1-SNAPSHOT.jar"]
