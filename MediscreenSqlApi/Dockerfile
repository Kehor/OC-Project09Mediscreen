FROM openjdk:11
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} MediscreenSqlApi-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "MediscreenSqlApi-0.0.1-SNAPSHOT.jar"]