FROM openjdk:11
ENV PROPOSTA_ACTIVE_PROFILE=dev
ENV PROPOSTA_SERVER_PORT=8080
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE $PROPOSTA_SERVER_PORT
