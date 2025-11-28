# Expense & Income Tracker

Simple desktop budget tracker (Java Swing) for tracking expenses and incomes. Easy to run, no extra setup.

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

Just use the scripts in the project root:

```bash
# For the command-line app
./cli.sh

# For the GUI app
./ui.sh
```

Both scripts compile the code before running. No need to run javac yourself.

If you want to run the test harness:

```bash
javac -d . src/expense_income_tracker/*.java test/*.java
java test.TestTracker

## Developer notes: code structure & how it works

- Location and structure
	- Source code lives under `src/expense_income_tracker/` (Java package `expense_income_tracker`).
	- Tests are under `test/junit/` and include a small JUnit 5 test suite that validates core model behaviour.
	- Scripts `cli.sh`, `ui.sh` and `test/runtests.sh` provide quick ways to run the CLI, GUI and tests respectively.

- Main responsibilities
	- `ExpenseIncomeEntry` — simple POJO for a single record (date, description, amount, type).
	- `ExpenseIncomeTableModel` — in-memory `AbstractTableModel` that stores a `List<ExpenseIncomeEntry>`, offers add/edit/remove/sort/getBalance behaviors, and is used by the GUI's `JTable`.
	- `ExpensesIncomesTracker` — builds the UI with Swing input fields and wiring to `ExpenseIncomeTableModel`. It also performs simple input parsing (parsing amount) and business logic: expenses are negated before insertion; income remains positive.

- How the code works (flow)
	1. UI: user provides Date, Description, Amount, and Type (Expense/Income) and presses Add.
	2. The code parses the amount as a `double`. If type is `Expense`, the amount sign is negated prior to creating an `ExpenseIncomeEntry`.
	3. `ExpenseIncomeTableModel.addEntry` stores the new entry and `getBalance()` computes the running balance as the sum of `amount` values.

- Notes for developers
	- The model is intentionally minimal; additional validation is performed in the GUI only for user input parsing and exceptions.
	- There is a tiny, documented compiler warning about generics in `ExpenseIncomeTableModel` due to raw use in a collection context — this is non-fatal and can be addressed if desired.
	- Unit tests use JUnit 5. To run tests, run `./test/runtests.sh` which will download the junit-platform-console-standalone jar and run the tests.

```

## Optional: FlatLaf (restore original look-and-feel)

1. Download the FlatLaf jar (e.g. `flatlaf-3.2.5.jar`) and place it into `lib/`.
2. Compile including `lib/*` on the classpath:

```bash
mkdir -p out
javac -d out -cp "lib/*" src/expense_income_tracker/*.java
java -cp "out:lib/*" expense_income_tracker.ExpensesIncomesTracker
```

Alternatively, use Maven/Gradle and declare `com.formdev:flatlaf` as a dependency.

## Note

The .desktop launcher was removed. Just use the scripts above to run the app.

## Future work / TODO

- Add persistent storage (CSV/JSON or embedded DB) so entries survive restarts.
- Add filtering, sorting UI controls, export/import features.
- Add unit tests (JUnit) and automate builds (Maven or Gradle).
- Improve input validation and date parsing.

## License & contributing

This is a copy of an existing public repo used for learning and adaptation. Check the original repository for licensing; when contributing back, keep proper attribution and respect the original license.

---

If you'd like, I can add a small `pom.xml` (Maven) to manage the FlatLaf dependency and provide `mvn exec:java` run targets.
