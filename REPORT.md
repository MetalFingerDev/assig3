# Expense & Income Tracker

**Name:** [Prajwal Pal](https://github.com/MetalFingerDev/) |
**Reg. no:** 22BCE10643 |
**Date:** 28 Nov 2025

## Introduction

A simple desktop app to track expenses and incomes. I built it to learn Java Swing and get hands-on with a real-world budget tracker.

## Project Objective

Make it super easy for people to quickly log and review incomes/expenses (no Excel, no cloud, no setup). The app demonstrates Swing-based GUIs, a CLI, and simple data model and testing.

## Problem Statement

People need a quick way to record and review their spending and income, without using spreadsheets or online tools.

## Functional Requirements

- Add, edit, and remove entries (expense/income)
- View all entries in a table
- See current balance
- CLI and GUI versions

Major functional modules:

- Entry Management: add/edit/delete entries (date, type, category, amount, description).
- View & Reporting: table view, running balance, and simple summary/total per type.
- Interface & Interaction: CLI for quick testing + GUI (Swing) for day-to-day use.

Inputs / Outputs

- Inputs: entry fields (date, amount, type, category, description) via CLI or GUI.
- Outputs: table rows, current balance label, basic CLI listings, and console messages.

User workflow:

1. Start app (CLI or GUI) using scripts.
2. Add one or more entries.
3. View table and balance; edit or delete entries if needed.
4. (Future) Save or export entries to CSV/JSON.

## Non-functional Requirements

- Easy to use
- Simple error handling
- Fast and lightweight
- Code is modular and easy to maintain

Additional non-functional requirements:

- Usability: minimal clicks in GUI and clear CLI prompts.
- Reliability: basic validation and graceful failure for malformed input.
- Maintainability: small, well-named classes and package structure to make future changes easy.
- Performance: responsive UI for hundreds of entries (no heavy filtering/indexing required now).
- Security/Privacy: no external data transfer; data stays local (future persistent storage optionally encryptable).

## System Architecture

Uses a basic MVC pattern with Java Swing. See [architecture.md](docs/architecture.md) for details.

High-level design notes:

- Model: `ExpenseIncomeEntry`, `ExpenseIncomeTableModel` hold data and validation.
- View: Swing frames and the CLI text prompts for user interaction.
- Controller/Driver: `Expense_Income_Tracker`, `EvaluatorDriver` glue the UI to the model and the table.

## Design Diagrams

UML/class diagrams are in [architecture.md](docs/architecture.md).

Other design artifacts to include (briefly):

- Use Case and Workflow diagrams: show add/view/edit/delete flow (see `docs/architecture.md`).
- Sequence or component diagrams: show how UI interacts with the model and table model.

## Design Decisions & Rationale

I chose Java Swing for cross-platform desktop UI. Simplified the code to avoid extra dependencies and make it easy to run. Scripts help users launch the app without setup.

## Implementation Details

- All code is in `src/expense_income_tracker/`
- Scripts `cli.sh` and `ui.sh` compile and run the app
- Test harness in `test/TestTracker.java`

Files / modules of note:

- `ExpenseIncomeEntry.java` — simple data POJO with validation.
- `ExpenseIncomeTableModel.java` — TableModel for JTable.
- `Expense_Income_Tracker.java` — main/GUI logic.
- `ExpensesIncomesTracker.java` — top-level controller/manager.
- `EvaluatorDriver.java` — CLI driver/test runner.
- `test/TestTracker.java` & `test/ExpenseTracker.java` — basic test harnesses.

Tech / tools: Java 11+ (or compatible), Java Swing for UI, Git for version control, and plain file scripts to run the project.

## Screenshots / Results

I added a few screenshots to capture the main flows. Drop your images into `docs/screenshots/` and give them the filenames below — I used casual captions so you can copy/paste quickly.

( screenshots and filenames in `docs/screenshots/`):

![GUI - empty](docs/screenshots/gui-empty.png)
![GUI - entry added](docs/screenshots/gui-entry-added.png)
![GUI - multi entries](docs/screenshots/gui-multi-entries.png)
![GUI - edit/remove](docs/screenshots/gui-edit-remove.png)
![CLI - menu](docs/screenshots/cli-menu.png)
![CLI - add prompt](docs/screenshots/cli-add.png)
![CLI - list & balance](docs/screenshots/cli-list-balance.png)

## Testing Approach

Manual testing via CLI and GUI. Used the test harness to check table logic and balance calculation.

Testing notes:

- Manual: run `cli.sh` to exercise add/list/delete, run `ui.sh` for GUI verification.
- Unit/test harness: `test/TestTracker.java` covers the balance and table model behavior; add JUnit later for automated CI.
- Edge cases considered: zero or negative amounts, empty fields, malformed dates.

## Challenges Faced

- Making the UI simple and bug-free
- Removing external dependencies (FlatLaf)
- Keeping code clean and easy to run

## Learnings & Key Takeaways

- How to build a desktop app with Java Swing
- Importance of modular code and error handling
- How to document and organize a project for others

## Future Enhancements

- Save/load entries to file (CSV/JSON)
- Better input validation
- Add sorting/filtering in the UI
- Automated tests (JUnit)

Other ideas (short):

- Add CSV import/export and persistent storage.
- Add categories and monthly summaries.
- Add small set of automated unit tests and a simple GitHub Actions workflow.

## References

- Original repo: https://github.com/bhavesh003/Expense_Income_Tracker

## Repo checklist (what's included)

- README.md: covers title, overview, how to run, and commands.
- statement.md: (optional) short problem statement, scope, target users — add if needed.
- Source: in `src/expense_income_tracker/` with scripts in repo root.
- Docs: `docs/architecture.md` includes class diagrams and architecture notes.

That's it — kept it short and casual. If you'd like, I can also add a `statement.md` and a couple quick UML PNGs into `docs/`.
