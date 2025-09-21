# ====== Builder Stage ======
FROM eclipse-temurin:21-jdk as builder
WORKDIR /build
COPY . .
RUN ./mvnw clean package -DskipTests

# ====== Runtime Stage ======
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy JAR
COPY --from=builder /build/target/omnitrix-0.0.1-SNAPSHOT.jar app.jar

# Copy all resource configs
COPY ./src/main/resources/ /app/config/

# Spring Boot config
ENV SPRING_CONFIG_LOCATION=file:/app/config/
ENV SPRING_PROFILES_ACTIVE=production

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
