package test;

import expense_income_tracker.ExpenseIncomeEntry;
import expense_income_tracker.ExpenseIncomeTableModel;

public class TestTracker {
    public static void main(String[] args) {
        ExpenseIncomeTableModel model = new ExpenseIncomeTableModel();
        model.addEntry(new ExpenseIncomeEntry("2025-11-01", "Grocery", -500.0, "Expense"));
        model.addEntry(new ExpenseIncomeEntry("2025-11-05", "Salary", 30000.0, "Income"));
        printModel(model);
    }

    // ...existing code...
}
