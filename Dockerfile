FROM openjdk:22

ARG FILE_JAR=target/date0404-0.0.1-SNAPSHOT.jar

ADD ${FILE_JAR} api-service.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/test
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=admin

ENTRYPOINT ["java", "-jar", "api-service.jar"]

EXPOSE 8080