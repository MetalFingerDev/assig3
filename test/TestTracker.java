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

    static void printModel(ExpenseIncomeTableModel m) {
        int rows = m.getRowCount();
        int cols = m.getColumnCount();
        double balance = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(m.getValueAt(r, c) + "\t");
            }
            System.out.println();
            balance += (double) m.getValueAt(r, 2);
        }
        System.out.println("Balance: Rs " + balance);
    }
}
