FROM openjdk:8-jre-alpine
VOLUME /tmp
ADD target/waes-technical-assignment-*.jar waes-technical-assignment.jar
ENTRYPOINT ["java", "-jar", "waes-technical-assignment.jar"]