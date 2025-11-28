# Expense & Income Tracker

Simple desktop budget tracker (Java Swing) that demonstrates a small MVC-style GUI app for recording expenses and incomes.

This repository is a copy / adaptation of the original project by Bhavesh — please see the credit below.

## Quick summary

- Purpose: track expenses and incomes in a table, calculate balance, support add/edit/remove operations.
- UI: Java Swing (JFrame, JTable, custom TableModel).
- Source: kept small and easy to run without an external build tool.

## Credit

This repository started from: https://github.com/bhavesh003/Expense_Income_Tracker

We credit the original author for the initial code and UI design. This fork/copy adapts and simplifies the project to make it buildable out-of-the-box and to track incremental changes.

## Changes we made to the original code

- Consolidated source files into `src/expense_income_tracker` and a single `test/` runner.
- Replaced an external FlatLaf dependency (used for the FlatDarkLaf look-and-feel) with the system LookAndFeel so the project compiles without needing extra jars. See `src/expense_income_tracker/ExpensesIncomesTracker.java` for the change.
- Verified and fixed small compile / runtime issues so `test/TestTracker` runs and prints expected output.
- Left a small `javac` unchecked-operations note in `ExpenseIncomeTableModel` (non-fatal). We preserved original behavior.

If you want the original FlatLaf look-and-feel later, see the notes under "Optional: FlatLaf" below.

## How to build and run

From the project root (Linux / bash):

```bash
# compile everything
mkdir -p out
javac -d out src/expense_income_tracker/*.java test/*.java

# run the simple console test harness
java -cp out test.TestTracker

# run the GUI application
java -cp out expense_income_tracker.ExpensesIncomesTracker
```

Notes:

- The `test/TestTracker` class demonstrates the table model without launching the Swing UI (good for quick checks).
- If you later want to use FlatLaf (original repo visual style), add the FlatLaf jar to a `lib/` folder and include it on the classpath when compiling/running (example below).

## Optional: FlatLaf (restore original look-and-feel)

1. Download the FlatLaf jar (e.g. `flatlaf-3.2.5.jar`) and place it into `lib/`.
2. Compile including `lib/*` on the classpath:

```bash
mkdir -p out
javac -d out -cp "lib/*" src/expense_income_tracker/*.java
java -cp "out:lib/*" expense_income_tracker.ExpensesIncomesTracker
```

Alternatively, use Maven/Gradle and declare `com.formdev:flatlaf` as a dependency.

## Future work / TODO

- Add persistent storage (CSV/JSON or embedded DB) so entries survive restarts.
- Add filtering, sorting UI controls, export/import features.
- Add unit tests (JUnit) and automate builds (Maven or Gradle).
- Improve input validation and date parsing.

## License & contributing

This is a copy of an existing public repo used for learning and adaptation. Check the original repository for licensing; when contributing back, keep proper attribution and respect the original license.

---

If you'd like, I can add a small `pom.xml` (Maven) to manage the FlatLaf dependency and provide `mvn exec:java` run targets.
