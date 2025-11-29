# Expense & Income Tracker

Simple desktop budget tracker (Java Swing)

## Quick start

- CLI: `./cli.sh`
- GUI: `./ui.sh`
- Run unit tests: `./test/runtests.sh` (downloads JUnit console runner if needed)

## What this repo contains

- `src/expense_income_tracker/` — Java sources (main package `expense_income_tracker`)
- `test/` — a demo harness and JUnit tests (`test/junit/`)
- `cli.sh`, `ui.sh` — convenience scripts to compile and run CLI/UI
- `test/runtests.sh` — convenience script to download JUnit console and run tests

## Key classes

- `ExpenseIncomeEntry` — simple POJO for a record (date, description, amount, type)
- `ExpenseIncomeTableModel` — in-memory `AbstractTableModel` holding the list of entries with add/edit/remove/sort and `getBalance()`.
- `ExpensesIncomesTracker` — UI (Swing JFrame) wiring the model and handling user actions (adding/editing/removing rows).

## How the model & UI behave

- `ExpenseIncomeTableModel.getBalance()` returns the sum of the `amount` column across all entries.
- The `ExpensesIncomesTracker` UI converts Expense values to negative by negating the parsed amount before creating `ExpenseIncomeEntry`.
- On Edit currently, the UI updates the running `balance` with `balance += amount` — this can miscount if the old amount isn't subtracted. For correctness, use `model.getBalance()` after edits or compute a delta.

## Tests & verification

- Small JUnit 5 suite at `test/junit/ExpenseIncomeTableModelTest.java` covering:
  - Balance calculation with mixed entries (income and expense)
  - Add/Edit/Remove behaviors in the `ExpenseIncomeTableModel`
  - Edge cases: zero and very large amounts; date strings are stored as-is (no parsing in model)
- Run tests: `./test/runtests.sh`

## Developer notes & next steps

- Consider switching the model date type from `String` to `LocalDate` to centralize validation and parsing.
- Add persistent storage (CSV/JSON/H2) for entries to survive restarts.
- Add CI (GitHub Actions) to run `test/runtests.sh` on PRs.
- Address the edit balance issue and unit test it.
