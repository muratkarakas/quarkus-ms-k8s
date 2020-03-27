#!/bin/bash

./mvnw clean package -Pnative -Dquarkus.native.container-runtime=docker -Dquarkus.container-image.build=true

# ./mvnw clean package -Dquarkus.kubernetes.deploy=true

#  ./mvnw clean package -Dquarkus.container-image.build=true


#./mvnw clean package -Pnative -Dquarkus.native.container-runtime=docker -Dquarkus.container-image.build=true   -Dquarkus.container-image.push=true   -Dquarkus.kubernetes.deploy=true


#./mvnw clean package -Pnative -Dquarkus.native.container-runtime=docker -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true 