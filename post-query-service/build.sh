#!/bin/bash

./mvnw clean package -Pnative -Dquarkus.native.container-runtime=docker -Dquarkus.container-image.build=true