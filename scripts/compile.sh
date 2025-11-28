#!/usr/bin/env bash
# Compile the project into out/ and include any jars in lib/ on the classpath
set -eu
ROOT_DIR="$(dirname "$0")/.."
mkdir -p "$ROOT_DIR/out"

# Build classpath including all jars in lib (if any)
LIB_CP=""
if compgen -G "$ROOT_DIR/lib/*" >/dev/null 2>&1; then
  LIB_CP="$ROOT_DIR/lib/*"
fi

echo "Compiling sources into out/ (lib on classpath: ${LIB_CP:-none})"
if [ -n "$LIB_CP" ]; then
  javac -d "$ROOT_DIR/out" -cp "$LIB_CP" "$ROOT_DIR/src/expense_income_tracker"/*.java "$ROOT_DIR/test"/*.java
else
  javac -d "$ROOT_DIR/out" "$ROOT_DIR/src/expense_income_tracker"/*.java "$ROOT_DIR/test"/*.java
fi

echo "Done. Use scripts/run_test.sh or scripts/run_gui.sh to run."