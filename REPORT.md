# Expense & Income Tracker

**Name:** Prajwal Pal (github profile)[https://github.com/MetalFingerDev/]
**Reg. no:** 22BCE10643
**Date:** 28 Nov 2025

## Introduction

A simple desktop app to track expenses and incomes. I built it to learn Java Swing and get hands-on with a real-world budget tracker.

## Problem Statement

People need a quick way to record and review their spending and income, without using spreadsheets or online tools.

## Functional Requirements

- Add, edit, and remove entries (expense/income)
- View all entries in a table
- See current balance
- CLI and GUI versions

## Non-functional Requirements

- Easy to use
- Simple error handling
- Fast and lightweight
- Code is modular and easy to maintain

## System Architecture

Uses a basic MVC pattern with Java Swing. See [architecture.md](docs/architecture.md) for details.

## Design Diagrams

UML/class diagrams are in [architecture.md](docs/architecture.md).

## Design Decisions & Rationale

I chose Java Swing for cross-platform desktop UI. Simplified the code to avoid extra dependencies and make it easy to run. Scripts help users launch the app without setup.

## Implementation Details

- All code is in `src/expense_income_tracker/`
- Scripts `cli.sh` and `ui.sh` compile and run the app
- Test harness in `test/TestTracker.java`

## Screenshots / Results

The GUI shows a table with expenses/incomes and a balance label. The CLI lets you add and list entries in the terminal. (Add screenshots if needed)

## Testing Approach

Manual testing via CLI and GUI. Used the test harness to check table logic and balance calculation.

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

## References

- Original repo: https://github.com/bhavesh003/Expense_Income_Tracker
