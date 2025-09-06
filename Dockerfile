FROM eclipse-temurin:24-jdk

WORKDIR /app

COPY target/Dodo-0.0.1-SNAPSHOT.jar /app/Dodo-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "Dodo-0.0.1-SNAPSHOT.jar"]
