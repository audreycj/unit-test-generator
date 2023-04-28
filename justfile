#!/usr/bin/env just --justfile

# maven build without tests
build:
   mvn -DskipTests clean package

# native build with GraalVM native-image
native-build:
   mvn -Pnative -DskipTests -X clean package native:compile
