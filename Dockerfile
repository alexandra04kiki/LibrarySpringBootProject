FROM openjdk:21-jdk-slim

WORKDIR /LibrarySpringBootProject

COPY . /LibrarySpringBootProject

RUN chmod +x gradlew

RUN ./gradlew clean build -x check -x test

CMD ["java", "-jar", "build/libs/LibrarySpringBootProject-0.0.1-SNAPSHOT.jar.jar"]