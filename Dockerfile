FROM eclipse-temurin:23-jdk
LABEL authors="NoutSpace"
WORKDIR /app
COPY build/libs/RESTAURANT-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 2005