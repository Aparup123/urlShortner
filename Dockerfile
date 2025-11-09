FROM alpine
RUN apk update
RUN apk upgrade
RUN apk add openjdk21

WORKDIR /usr/urlShortner
COPY . .

RUN chmod +x mvnw && ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/urlShortner.jar"]
