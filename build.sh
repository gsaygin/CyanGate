#!/bin/bash

mkdir -p executables

./mvnw clean package
cp target/demo-0.0.1-SNAPSHOT.jar ./executables