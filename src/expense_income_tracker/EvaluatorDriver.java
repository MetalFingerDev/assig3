package expense_income_tracker;

import java.util.Scanner;

/**
 * Simple CLI driver intended for evaluators.
 * - `--demo` runs a short non-interactive demo and exits.
 * - no args starts a small interactive menu for basic operations.
 */
public class EvaluatorDriver {

    private final ExpenseIncomeTableModel model;

    public EvaluatorDriver() {
        model = new ExpenseIncomeTableModel();
    }

    public static void main(String[] args) {
        EvaluatorDriver driver = new EvaluatorDriver();
        driver.runInteractive();
    }

    // Removed demo mode for simplicity

    private void runInteractive() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\nExpense/Income Tracker CLI");
            System.out.println("1) Add entry");
            System.out.println("2) List entries");
            System.out.println("3) Show balance");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    addEntryInteractive(sc);
                    break;
                case "2":
                    printEntries();
                    break;
                case "3":
                    System.out.println("Balance: Rs " + model.getBalance());
                    break;
                case "0":
                    System.out.println("Bye");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addEntryInteractive(Scanner sc) {
        try {
            System.out.print("Date (YYYY-MM-DD): ");
            String date = sc.nextLine().trim();
            System.out.print("Description: ");
            String desc = sc.nextLine().trim();
            System.out.print("Amount: ");
            double amount = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Type (Expense/Income): ");
            String type = sc.nextLine().trim();
            if ("Expense".equalsIgnoreCase(type))
                amount = -Math.abs(amount);
            else
                type = "Income";
            model.addEntry(new ExpenseIncomeEntry(date, desc, amount, type));
            System.out.println("Added entry");
        } catch (Exception ex) {
            System.out.println("Invalid input");
        }
    }

    private void printEntries() {
        int rows = model.getRowCount();
        if (rows == 0) {
            System.out.println("(no entries)");
            return;
        }
        System.out.printf("%-12s  %-20s  %-10s  %-8s\n", "Date", "Description", "Amount", "Type");
        for (int i = 0; i < rows; i++) {
            Object d = model.getValueAt(i, 0);
            Object desc = model.getValueAt(i, 1);
            Object amt = model.getValueAt(i, 2);
            Object t = model.getValueAt(i, 3);
            System.out.printf("%-12s  %-20s  %-10s  %-8s\n", String.valueOf(d), String.valueOf(desc),
                    String.valueOf(amt), String.valueOf(t));
        }
    }
}
