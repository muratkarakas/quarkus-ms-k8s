FROM openjdk:8-jdk-alpine
RUN apk add --update \
    curl \
    && rm -rf /var/cache/apk/*

RUN apk --no-cache add curl   

ADD maven/sb-product-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]