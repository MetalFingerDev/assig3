**Navigation**

- [REPORT.md](../REPORT.md)
- [README.md](../README.md)
- [Documentation](documentation.md)
- [Description](description.md)

# Architecture & Design

[< back to REPORT.md](../REPORT.md)

## Overview

This document describes the high-level architecture and design decisions for the Expense/Income Tracker application. It focuses on the core modules and their responsibilities, non-functional constraints, runtime setup, and known issues.

## Table of Contents

- [High-level architecture](#high-level-architecture)
- [Process flow](#process-flow)
- [UML / Class diagram (ASCII)](#uml--class-diagram-ascii)
- [Functional modules](#functional-modules)
- [Non-functional requirements](#non-functional-requirements)
- [Repository & runtime adjustments](#repository--runtime-adjustments)
- [Notes / known issues](#notes--known-issues)
- [Quick run & tests](#quick-run--tests)

## High-level architecture

- Type: Desktop Java Swing application (with an optional CLI test harness)
- Package: `src/expense_income_tracker`
- Main entry points:
  - GUI: `Expense_Income_Tracker.java` (launches the Swing UI)
  - CLI/Test: `EvaluatorDriver.java`, `test/TestTracker.java`

## Process flow

1. User launches the app using `ui.sh` (or `cli.sh` for CLI/test mode).
2. The main frame initializes a `JTable` backed by `ExpenseIncomeTableModel`.
3. Users add/edit/remove entries through the UI. The table model updates in-memory data and informs the table via listeners.
4. A simple summary or balance is computed from the model entries on demand.

## UML / Class diagram (ASCII)

Simple ASCII class diagram showing core relationships:

```
ExpenseIncomeTracker (UI)
└─ uses → ExpenseIncomeTableModel
	 └─ contains → List<ExpenseIncomeEntry>

ExpenseIncomeEntry
├─ date: String
├─ description: String
├─ amount: double
└─ type: String  ("expense" | "income")
```

## Functional modules

- ExpensesIncomesTracker (UI)

  - Provides the Swing `JTable` view backed by `ExpenseIncomeTableModel`.
  - Controls UI actions: addEntry(), editEntry(), removeEntry().

- ExpenseIncomeTableModel

  - Holds `entries: List<ExpenseIncomeEntry>`.
  - Public API: `addEntry(e)`, `editEntry(i, e)`, `removeEntry(i)`, `getValueAt(r, c)`.

- ExpenseIncomeEntry
  - Fields: `date: String`, `description: String`, `amount: double`, `type: String`.
  - Represents a single record.

## Non-functional requirements

- Minimal dependencies and a simple codebase that compiles with standard JDK.
- Concise separation of UI and model to ease future extension.
- Desktop-focused (Swing) for cross-platform compatibility.

## Repository & runtime adjustments

- Source consolidation: the project consolidates sources into `src/expense_income_tracker` to avoid duplicate files and inconsistent imports.
- Removed optional LAF dependency (FlatLaf) to keep the app runnable out-of-the-box; the app uses `UIManager.getSystemLookAndFeelClassName()` instead.
- Scripts provided:
  - `cli.sh` — compile and run CLI/test harness.
  - `ui.sh` — compile and run the Swing UI.

## Notes / known issues

- `ExpenseIncomeTableModel` triggers a compiler warning about generics; it is a non-fatal compile-time note and can be fixed by clarifying generic types in the model (low risk).
- Persistence: the app stores data in-memory only. No disk/DB persistence is implemented.
- Test harness: `test/TestTracker.java` is useful for quick verification.

## Quick run & tests

From the project root, use the following scripts:

```
./cli.sh   # run the CLI test harness
./ui.sh    # run the GUI application
```

Unit tests (if available) are under `test/junit/` and can be run using your preferred test runner (e.g., `javac` followed by `java org.junit.runner.JUnitCore ...` or with a build tool wrapper, if added).

## Contribution & Next steps

- Consider adding an in-memory-to-file persistence layer (CSV, JSON, or a small embedded DB like H2).
- Add clear unit tests for the model (`ExpenseIncomeTableModel`) to assert add/edit/remove behavior and balance calculation.
- Fix generic warnings in `ExpenseIncomeTableModel` by annotating generics properly.

---

If you'd like, I can also:

- add a small ASCII class diagram as a PNG or rendered UML file in `docs/screenshots/`.
- add a short README snippet that shows basic commands and expectations.

Edit completed by the documentation cleaner.
