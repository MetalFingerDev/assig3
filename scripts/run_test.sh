#!/usr/bin/env bash
# Run the simple test harness (test/TestTracker) from out/
set -eu
ROOT_DIR="$(dirname "$0")/.."
if [ ! -d "$ROOT_DIR/out" ]; then
  echo "out/ not found — compile first: scripts/compile.sh" >&2
  exit 1
fi

echo "Running test.TestTracker"
java -cp "$ROOT_DIR/out" test.TestTracker
