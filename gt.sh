#!/bin/bash

cd "$(dirname "$0")" || exit

LANGUAGE="$1"
shift
FUNCTION="$@"

java -jar target/unittest-generator.jar --language="$LANGUAGE" --function="$FUNCTION"
