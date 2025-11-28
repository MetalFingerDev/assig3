# Architecture & Design

This document describes the high-level architecture of the Expense & Income Tracker application.

## High level

The app uses a simple Model-View(-Controller) pattern with Java Swing:

- Model: `ExpenseIncomeEntry` (POJO) and `ExpenseIncomeTableModel` (extends `AbstractTableModel`).
- View / Controller: `ExpensesIncomesTracker` — a `JFrame` that contains input controls (text fields, combobox, buttons), a `JTable` backed by the table model and action listeners that act as the controller.
- Main entry points: `Expense_Income_Tracker` (simple main) and `ExpensesIncomesTracker` (GUI main).

## Components

- `ExpenseIncomeEntry` — data holder for a single record (date, description, amount, type).
- `ExpenseIncomeTableModel` — holds a `List<ExpenseIncomeEntry>` and provides table plumbing: `getRowCount()`, `getColumnCount()`, `getValueAt()`, plus helper methods for add/edit/remove, sort and basic filtering.
- `ExpensesIncomesTracker` — constructs the UI, wires event listeners for add/edit/remove, updates balance label, and manages input fields.

## Data flow

1. User enters a date, description, amount and type in the input area and clicks "Add".
2. `ExpensesIncomesTracker.addEntry()` creates an `ExpenseIncomeEntry` and calls `ExpenseIncomeTableModel.addEntry(...)`.
3. The table model updates its internal list and fires table events to refresh the `JTable` view.
4. The GUI updates balance and clears inputs.

## UML

ExpensesIncomesTracker - tableModel: ExpenseIncomeTableModel - table: JTable + addEntry() + editEntry() + removeEntry()

ExpenseIncomeTableModel - entries: List<ExpenseIncomeEntry> + addEntry(e) + editEntry(i,e) + removeEntry(i) + getValueAt(r,c)

ExpenseIncomeEntry - date: String - description: String - amount: double - type: String

## Non-functional

- Simple codebase, minimal dependencies, compiles with the JDK.
- Easy to extend: model and table are separate, so adding features is simple.
- Desktop-focused: built with Swing, works cross-platform.

## Notes / known issues

- `ExpenseIncomeTableModel` triggers a compiler note about generics (not fatal).
- Data is in-memory only for now.
- Use `cli.sh` or `ui.sh` to launch the app.
