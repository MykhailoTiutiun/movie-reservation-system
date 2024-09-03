FROM openjdk:17-jdk-alpine
LABEL maintainer="MykhailoTiutiun <mykhailotiutiun@gmail.com>"
COPY ./target/movie-reservation-system-*.jar movie-reservation-system.jar
ENTRYPOINT ["java", "-jar", "/movie-reservation-system.jar"]