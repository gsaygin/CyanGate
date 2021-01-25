#!/bin/bash

mkdir -p executables

mvn clean package
cp target/demo-0.0.1-SNAPSHOT.jar ./executables