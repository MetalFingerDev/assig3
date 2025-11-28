package expense_income_tracker;

import javax.swing.*;
import java.awt.*;

public class ExpensesIncomesTracker extends JFrame {

    private final ExpenseIncomeTableModel tableModel;
    private final JTable table;
    private final JTextField dateField;
    private final JTextField descriptionField;
    private final JTextField amountField;
    private final JComboBox<String> typeCombobox;
    private final JButton addButton;
    private final JButton editButton;
    private final JButton removeButton;
    private final JLabel balanceLabel;
    private double balance;

    public ExpensesIncomesTracker() {
        try {
            // Use the system look and feel to avoid external FlatLaf dependency.
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace(); // Print the exception details
            System.err.println("Failed to set LookAndFeel");
        }

        tableModel = new ExpenseIncomeTableModel();
        table = new JTable(tableModel);
        dateField = new JTextField(10);
        descriptionField = new JTextField(20);
        amountField = new JTextField(10);
        typeCombobox = new JComboBox<>(new String[] { "Expense", "Income" });
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        removeButton = new JButton("Remove");
        balanceLabel = new JLabel("Balance: Rs " + balance);

        addButton.addActionListener(e -> addEntry());
        editButton.addActionListener(e -> editEntry());
        removeButton.addActionListener(e -> removeEntry());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Date(YYYY-MM-DD)"));
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Description"));
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Amount"));
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Type"));
        inputPanel.add(typeCombobox);

        inputPanel.add(addButton);
        inputPanel.add(editButton);
        inputPanel.add(removeButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(balanceLabel);
        setLayout(new BorderLayout());

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setTitle("Budget Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addEntry() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String type = (String) typeCombobox.getSelectedItem();
            if (type.equals("Expense"))
                amount *= -1;
            ExpenseIncomeEntry entry = new ExpenseIncomeEntry(dateField.getText(), descriptionField.getText(), amount,
                    type);
            tableModel.addEntry(entry);
            balance += amount;
            balanceLabel.setText("Balance: Rs." + balance);
            clearInputFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editEntry() {
        int row = table.getSelectedRow();
        if (row == -1)
            return;
        try {
            double amount = Double.parseDouble(amountField.getText());
            String type = (String) typeCombobox.getSelectedItem();
            if (type.equals("Expense"))
                amount *= -1;
            ExpenseIncomeEntry entry = new ExpenseIncomeEntry(dateField.getText(), descriptionField.getText(), amount,
                    type);
            tableModel.editEntry(row, entry);
            balance += amount;
            balanceLabel.setText("Balance: Rs." + balance);
            clearInputFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeEntry() {
        int row = table.getSelectedRow();
        if (row == -1)
            return;
        double amt = (double) table.getValueAt(row, 2);
        tableModel.removeEntry(row);
        balance -= amt;
        balanceLabel.setText("Balance: Rs." + balance);
    }

    private void clearInputFields() {
        dateField.setText("");
        descriptionField.setText("");
        amountField.setText("");
        typeCombobox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ExpensesIncomesTracker());
    }
}
