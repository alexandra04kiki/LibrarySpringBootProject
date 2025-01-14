FROM openjdk:21-jdk

# Setează directorul de lucru
WORKDIR /app

# Copiază totul din folderul local în container
COPY . /app

# Dă permisiuni de execuție pentru gradlew
RUN chmod +x gradlew

# Rulează comanda de build
RUN ./gradlew clean build -x check -x test

# Comanda pentru a rula aplicația
CMD ["java", "-jar", "build/libs/LibrarySpringBootProject-0.0.1-SNAPSHOT.jar"]
