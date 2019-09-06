#!/bin/bash

./mvnw clean package -Pnative -Dnative-image.docker-build=true

docker build -f src/main/docker/Dockerfile.native -t quarkus-user-service .


