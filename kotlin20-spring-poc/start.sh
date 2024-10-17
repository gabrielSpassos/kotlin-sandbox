#!/bin/bash

echo "Build kotlin project"
./mvnw clean install -DskipTests

echo "Start Project"
podman-compose up