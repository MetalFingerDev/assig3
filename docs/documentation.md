# Development & Documentation

This file documents the development process used to copy and adapt the original Expense_Income_Tracker project, lists the Java features used, and explains how future changes will be tracked.

## How this copy was produced

1. Cloned the original public repository (credit: https://github.com/bhavesh003/Expense_Income_Tracker).
2. Compared the provided source folders and consolidated all Java source files under `src/expense_income_tracker` to avoid duplicate locations.
3. Fixed a runtime/build friction point: the original project used `com.formdev.flatlaf.FlatDarkLaf` which requires an external jar. To make the repo buildable out-of-the-box, the GUI class was updated to use the system LookAndFeel. That change is localized to `src/expense_income_tracker/ExpensesIncomesTracker.java`.
4. Compiled the model classes and ran a simple test harness `test/TestTracker.java` to confirm behaviour (two rows added, balance calculated correctly).

## Summary of code changes

- Replaced FlatLaf usage with `UIManager.getSystemLookAndFeelClassName()` to avoid an external dependency.
- Consolidated sources into a single `src/expense_income_tracker` package to keep imports and package declarations consistent.
- Verified compilation output and noted a non-fatal unchecked-operations warning in `ExpenseIncomeTableModel`.

## Java features & libraries used in the project

- Packages and modular source layout (package: `expense_income_tracker`).
- Java Swing: `JFrame`, `JPanel`, `JTable`, `JScrollPane`, `JTextField`, `JComboBox`, `JButton`, `JLabel`, `JOptionPane`.
- Table plumbing: `AbstractTableModel` for `ExpenseIncomeTableModel`.
- Collections API: `List`, `ArrayList`, `Collections.sort`, `Comparator`.
- Exception handling: `try/catch` for LookAndFeel and number parsing.
- Lambdas and method references: used for action listeners (anonymous lambdas).
- Basic Java types: `String`, `double`, primitives and boxing/unboxing when using `JTable` value retrieval.

## Testing & verification

- A simple test runner `test/TestTracker` adds two entries and prints the table rows and balance — useful for headless checks without launching the UI.
- No JUnit tests were added yet; adding JUnit and a `pom.xml` (or Gradle build) is recommended for automated testing.


## Development workflow & tracking planned changes

- Use feature branches for each new task (e.g. `feature/persistence`, `feature/export-csv`).
- Keep the `test` branch or create a CI-enabled branch for automated verification before merging.
- Use clear commit messages and update `docs/documentation.md` and `docs/architecture.md` when design changes.
- Keep a simple TODO list in the repo root or use GitHub Issues for larger tasks.

## Planned features to add (short roadmap)

- Persistence: save/load entries (CSV, JSON, or embedded DB like H2).
- Improved validation and date parsing using `java.time.LocalDate` and `DateTimeFormatter`.
- Sorting and filtering UI controls (dropdowns / clickable column headers).
- Export/import CSV and simple reporting (monthly totals, category summaries).
- Unit tests and CI pipeline (GitHub Actions) for compile + test runs.

---

