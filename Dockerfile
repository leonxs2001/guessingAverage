FROM openjdk:11
MAINTAINER docker@thb.de

# Kopiere die JAR-Datei in das Image
ADD ./target/guessingAverage-0.0.1-SNAPSHOT.jar /service.jar

# Setze den Arbeitsverzeichnis
WORKDIR /

# Port 8080 freigeben
EXPOSE 8080

# Java-Anwendung ausführen
CMD ["java", "-jar", "/service.jar"]
