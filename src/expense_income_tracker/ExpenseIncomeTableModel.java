package expense_income_tracker;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ExpenseIncomeTableModel extends AbstractTableModel {

    private final List<ExpenseIncomeEntry> entries;
    private final String[] columnNames = { "Date", "Description", "Amount", "Type" };

    public ExpenseIncomeTableModel() {
        entries = new ArrayList<>();
    }

    public void addEntry(ExpenseIncomeEntry entry) {
        entries.add(entry);
        fireTableRowsInserted(entries.size() - 1, entries.size() - 1);
    }

    public void editEntry(int rowIndex, ExpenseIncomeEntry updatedEntry) {
        entries.set(rowIndex, updatedEntry);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public void removeEntry(int rowIndex) {
        entries.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void sortByColumn(int columnIndex) {
        Comparator<ExpenseIncomeEntry> comparator;
        switch (columnIndex) {
            case 0:
                comparator = Comparator.comparing(ExpenseIncomeEntry::getDate,
                        Comparator.nullsFirst(String::compareTo));
                break;
            case 1:
                comparator = Comparator.comparing(ExpenseIncomeEntry::getDescription,
                        Comparator.nullsFirst(String::compareTo));
                break;
            case 2:
                comparator = Comparator.comparingDouble(ExpenseIncomeEntry::getAmount);
                break;
            case 3:
                comparator = Comparator.comparing(ExpenseIncomeEntry::getType,
                        Comparator.nullsFirst(String::compareTo));
                break;
            default:
                comparator = Comparator.comparing(e -> String.valueOf(getColumnValue(e, columnIndex)));
        }
        entries.sort(comparator);
        fireTableDataChanged();
    }

    public double getBalance() {
        return entries.stream().mapToDouble(ExpenseIncomeEntry::getAmount).sum();
    }

    // Removed unused formatDateColumn and filterByType for simplicity

    private Object getColumnValue(ExpenseIncomeEntry entry, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return entry.getDate();
            case 1:
                return entry.getDescription();
            case 2:
                return entry.getAmount();
            case 3:
                return entry.getType();
            default:
                return null;
        }
    }

    @Override
    public int getRowCount() {
        return entries.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ExpenseIncomeEntry entry = entries.get(rowIndex);
        return getColumnValue(entry, columnIndex);
    }
}
