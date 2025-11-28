#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")"/.. && pwd)"
LIB_DIR="$ROOT/lib"
JAR_NAME="junit-platform-console-standalone-1.9.3.jar"
JAR_PATH="$LIB_DIR/$JAR_NAME"

mkdir -p "$LIB_DIR"
if [ ! -f "$JAR_PATH" ]; then
  echo "Downloading junit-platform-console-standalone..."
  curl -L -o "$JAR_PATH" \
    "https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/$JAR_NAME"
fi

echo "Compiling sources..."
mkdir -p "$ROOT/out"
javac -d "$ROOT/out" $(find "$ROOT/src" -name "*.java")

echo "Compiling tests..."
javac -cp "$JAR_PATH:$ROOT/out" -d "$ROOT/out" $(find "$ROOT/test/junit" -name "*.java")

echo "Running tests..."
java -jar "$JAR_PATH" --class-path "$ROOT/out" --scan-class-path

echo "Done"
