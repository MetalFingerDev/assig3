package test.junit;

import expense_income_tracker.ExpenseIncomeEntry;
import expense_income_tracker.ExpenseIncomeTableModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpenseIncomeTableModelTest {

    @Test
    public void testBalanceCalculationMixed() {
        ExpenseIncomeTableModel model = new ExpenseIncomeTableModel();
        model.addEntry(new ExpenseIncomeEntry("2025-11-01", "Grocery", -500.0, "Expense"));
        model.addEntry(new ExpenseIncomeEntry("2025-11-05", "Salary", 30000.0, "Income"));
        assertEquals(29500.0, model.getBalance(), 0.0001);
    }

    @Test
    public void testAddEditRemoveBehavior() {
        ExpenseIncomeTableModel model = new ExpenseIncomeTableModel();
        assertEquals(0, model.getRowCount());

        // add
        ExpenseIncomeEntry e1 = new ExpenseIncomeEntry("2025-11-01", "Grocery", -500.0, "Expense");
        ExpenseIncomeEntry e2 = new ExpenseIncomeEntry("2025-11-05", "Salary", 30000.0, "Income");
        model.addEntry(e1);
        model.addEntry(e2);
        assertEquals(2, model.getRowCount());
        assertEquals(-500.0, (double) model.getValueAt(0, 2), 0.0001);
        assertEquals("Salary", (String) model.getValueAt(1, 1));

        // edit (replace entry at index 0)
        ExpenseIncomeEntry updated = new ExpenseIncomeEntry("2025-11-01", "Groceries - large", -750.0, "Expense");
        model.editEntry(0, updated);
        assertEquals("Groceries - large", (String) model.getValueAt(0, 1));
        assertEquals(-750.0, (double) model.getValueAt(0, 2), 0.0001);
        // new balance should reflect updated amounts
        assertEquals(29250.0, model.getBalance(), 0.0001);

        // remove
        model.removeEntry(0);
        assertEquals(1, model.getRowCount());
        assertEquals(30000.0, model.getBalance(), 0.0001);
    }

    @Test
    public void testEdgeCaseValidation() {
        ExpenseIncomeTableModel model = new ExpenseIncomeTableModel();
        // Zero amount allowed
        model.addEntry(new ExpenseIncomeEntry("2025-11-07", "No-op", 0.0, "Income"));
        assertEquals(0.0, model.getBalance(), 0.0001);

        // Date string is not parsed by model: it should be stored as-is
        String weirdDate = "07-11-2025T10:00:00Z";
        model.addEntry(new ExpenseIncomeEntry(weirdDate, "Weird date", 100.0, "Income"));
        assertEquals(1, model.getRowCount());
        assertEquals(100.0, model.getBalance(), 0.0001);
        assertEquals(weirdDate, (String) model.getValueAt(0, 0));

        // Large amounts are supported
        model = new ExpenseIncomeTableModel();
        model.addEntry(new ExpenseIncomeEntry("2025-11-01", "BigIncome", 1e12, "Income"));
        assertEquals(1e12, model.getBalance(), 0.0001);
    }
}
