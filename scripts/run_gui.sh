#!/usr/bin/env bash
# Run the GUI application (ExpensesIncomesTracker) using out/ and lib/* on classpath
set -eu
ROOT_DIR="$(dirname "$0")/.."

if [ ! -d "$ROOT_DIR/out" ]; then
  echo "out/ not found — compile first: scripts/compile.sh" >&2
  exit 1
fi

CP="$ROOT_DIR/out"
if compgen -G "$ROOT_DIR/lib/*" >/dev/null 2>&1; then
  CP="$CP:$ROOT_DIR/lib/*"
fi

echo "Running GUI with classpath: $CP"
java -cp "$CP" expense_income_tracker.ExpensesIncomesTracker
