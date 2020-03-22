#!/bin/bash

./mvnw package -Pnative -Dquarkus.native.container-runtime=docker

docker build -f src/main/docker/Dockerfile.native -t mkarakas/quarkus-post-service .


