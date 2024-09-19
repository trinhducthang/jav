FROM openjdk:22

ARG FILE_JAR=target/user-bank-manager-0.0.1-SNAPSHOT.jar

ADD ${FILE_JAR} api-service.jar

ENV SPRING_DATASOURCE_URL=jdbc:mysql://mysql-3f75728b-thang79cfc6cdz-0d9d.h.aivencloud.com:11085/defaultdb
ENV SPRING_DATASOURCE_USERNAME=avnadmin
ENV SPRING_DATASOURCE_PASSWORD=AVNS_dn-2gAgkgCbZkyN34uH

ENTRYPOINT ["java", "-jar", "api-service.jar"]

EXPOSE 8080