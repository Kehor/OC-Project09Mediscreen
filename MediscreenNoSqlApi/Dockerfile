FROM openjdk:11
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} MediscreenNoSqlApi-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "MediscreenNoSqlApi-0.0.1-SNAPSHOT.jar"]