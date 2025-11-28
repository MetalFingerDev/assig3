#!/usr/bin/env bash
# Download FlatLaf jar into lib/ (idempotent)
set -eu
mkdir -p "$(dirname "$0")/../lib"
LIB_DIR="$(dirname "$0")/../lib"
JAR_NAME="flatlaf-3.2.5.jar"
URL="https://repo1.maven.org/maven2/com/formdev/flatlaf/3.2.5/flatlaf-3.2.5.jar"
TARGET="$LIB_DIR/$JAR_NAME"

if [ -f "$TARGET" ]; then
  echo "FlatLaf already exists at $TARGET"
  exit 0
fi

echo "Downloading FlatLaf from $URL to $TARGET"
if command -v curl >/dev/null 2>&1; then
  curl -fSL -o "$TARGET" "$URL"
elif command -v wget >/dev/null 2>&1; then
  wget -O "$TARGET" "$URL"
else
  echo "Error: curl or wget required to download FlatLaf.\nPlease download $URL manually and place it in $LIB_DIR." >&2
  exit 2
fi

echo "Downloaded $TARGET"
