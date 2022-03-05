FROM openjdk:11
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} Mediscreen-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Mediscreen-0.0.1-SNAPSHOT.jar"]