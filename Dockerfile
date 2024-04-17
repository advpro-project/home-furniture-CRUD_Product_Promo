FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean bootJar

FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS runner

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java"]
CMD ["-jar", "app.jar"]