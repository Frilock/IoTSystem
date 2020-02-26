FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/iot-system.jar
ARG postgres_host=iotfox.ru
ARG broker_host=iotfox.ru
COPY ${JAR_FILE} .

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://$postgres_host:20004/IoTSystemDB \
    SPRING_DATASOURCE_USERNAME=postgres \
    SPRING_DATASOURCE_PASSWORD=1234 \
    JWT_SECRET=PlSRo0LDxSq4VcMDozCcS3Ofaesf3dLombdgCiGPA4wFcy1rx90ErgNhlybKQCHG \
    BROKER=tcp://$broker_host:20021 \
    WS_SERVER_HOST=localhost \
    WS_SERVER_PORT=8082

ENTRYPOINT ["java","-jar","/iot-system.jar"]
