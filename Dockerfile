# ====== Builder Stage ======
FROM eclipse-temurin:21-jdk as builder
WORKDIR /build
COPY . .
RUN ./mvnw clean package -DskipTests

# ====== Runtime Stage ======
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy jar
COPY --from=builder /build/target/omnitrix-0.0.1-SNAPSHOT.jar app.jar

# Copy prod config
COPY ./src/main/resources/application-production.properties /app/config/application-prod.properties

# (Optional) Copy .env if your app parses it via dotenv-java
COPY ./.env /app/.env

# Spring Boot config
ENV SPRING_CONFIG_LOCATION=file:/app/config/
ENV SPRING_PROFILES_ACTIVE=production

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
