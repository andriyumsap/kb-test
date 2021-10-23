FROM openjdk:12

WORKDIR /app/kitabisa

COPY /target/kitabisa-1.0.jar ./kitabisa-1.0.jar

CMD ["java", "-jar", "kitabisa-1.0.jar"]