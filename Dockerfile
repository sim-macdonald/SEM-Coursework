FROM openjdk:latest
COPY ./target/SEM_Coursework-1.2.1-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SEM_Coursework-1.2.1-jar-with-dependencies.jar"]