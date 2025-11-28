import java.util.*;

/**
 * Single-file compact version for easy evaluation.
 * Compile with: javac ExpenseTracker.java
 * Run with: java ExpenseTracker
 */
public class ExpenseTracker {

    static class Entry {
        final String date;
        final String desc;
        final double amount;
        final String type;

        Entry(String d, String s, double a, String t) {
            date = d;
            desc = s;
            amount = a;
            type = t;
        }

        public String toString() {
            return String.format("%s\t%s\t%s\t%s", date, desc, amount, type);
        }
    }

    static class Model {
        final List<Entry> e = new ArrayList<>();

        void add(Entry en) {
            e.add(en);
        }

        int rows() {
            return e.size();
        }

        Entry get(int i) {
            return e.get(i);
        }

        double balance() {
            double s = 0;
            for (Entry x : e)
                s += x.amount;
            return s;
        }

        void sortByCol(int c) {
            Comparator<Entry> cmp;
            switch (c) {
                case 0:
                    cmp = Comparator.comparing(x -> x.date, Comparator.nullsFirst(Comparator.naturalOrder()));
                    break;
                case 1:
                    cmp = Comparator.comparing(x -> x.desc, Comparator.nullsFirst(Comparator.naturalOrder()));
                    break;
                case 2:
                    cmp = Comparator.comparingDouble(x -> x.amount);
                    break;
                case 3:
                    cmp = Comparator.comparing(x -> x.type, Comparator.nullsFirst(Comparator.naturalOrder()));
                    break;
                default:
                    return;
            }
            Collections.sort(e, cmp);
        }
    }

    public static void main(String[] args) {
        Model m = new Model();
        // demo entries
        m.add(new Entry("2025-11-01", "Grocery", -500.0, "Expense"));
        m.add(new Entry("2025-11-05", "Salary", 30000.0, "Income"));

        if (args.length > 0 && ("--list".equals(args[0]) || "-l".equals(args[0]))) {
            print(m);
            return;
        }

        // Default behaviour: print demo and balance
        print(m);
        System.out.println("Balance: Rs " + m.balance());
    }

    private static void print(Model m) {
        if (m.rows() == 0) {
            System.out.println("(no entries)");
            return;
        }
        System.out.println("Date\tDescription\tAmount\tType");
        for (int i = 0; i < m.rows(); i++)
            System.out.println(m.get(i));
    }
}
