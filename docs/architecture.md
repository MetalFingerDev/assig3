# Architecture & Design

High-level architecture:

- CLI Application (Main)

Process flow (textual):


UML / Class diagram (ASCII):

Main

Functional modules:

Non-functional requirements:


ExpensesIncomesTracker - tableModel: ExpenseIncomeTableModel - table: JTable + addEntry() + editEntry() + removeEntry()

ExpenseIncomeTableModel - entries: List<ExpenseIncomeEntry> + addEntry(e) + editEntry(i,e) + removeEntry(i) + getValueAt(r,c)

ExpenseIncomeEntry - date: String - description: String - amount: double - type: String

## Non-functional

- Simple codebase, minimal dependencies, compiles with the JDK.
- Easy to extend: model and table are separate, so adding features is simple.
- Desktop-focused: built with Swing, works cross-platform.

## Repository & runtime adjustments

- The project is a simplified copy of the original public repository (credit: https://github.com/bhavesh003/Expense_Income_Tracker). Sources were consolidated into a single package: `src/expense_income_tracker` to avoid duplicate locations and inconsistent imports.
- To reduce friction and avoid an extra dependency, the original theme library (FlatLaf) was removed. The GUI now uses the system look-and-feel via `UIManager.getSystemLookAndFeelClassName()`, so the app runs out-of-the-box without needing external JARs.
- The CLI (`EvaluatorDriver` / `test/TestTracker.java`) and `cli.sh`/`ui.sh` scripts are provided to compile and run the app quickly for manual verification.

## Notes / known issues

- `ExpenseIncomeTableModel` triggers a compiler note about generics (not fatal).
- Data is in-memory only for now.
- Use `cli.sh` or `ui.sh` to launch the app.

Additional notes:

- A quick test harness (`test/TestTracker.java`) can be used to verify basic model behaviour: it adds two rows and prints table contents and balance to confirm business logic.
- The code intentionally keeps dependencies minimal—adding new UI LAF libraries is optional and should be documented if introduced in future.
