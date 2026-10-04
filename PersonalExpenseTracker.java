package org.MiniProject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

enum Category {
    FOOD,
    TRAVEL,
    SHOPPING,
    BILLS,
    OTHER
}

class Expense {

    int id;
    String name;
    double amount;
    Category category;
    LocalDate date;

    Expense(int id, String name, double amount, Category category) {

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
    }

    void display() {

        System.out.println(
                "ID: " + id +
                " | Name: " + name +
                " | Amount: Rs." + amount +
                " | Category: " + category +
                " | Date: " + date
        );
    }
}

public class PersonalExpenseTracker {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Expense> expenses = new ArrayList<>();

    public static void addExpense() {

        System.out.print("\nEnter expense id: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter expense name: ");
        String name = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        System.out.println("\nSelect Category");
        System.out.println("1. Food");
        System.out.println("2. Travel");
        System.out.println("3. Shopping");
        System.out.println("4. Bills");
        System.out.println("5. Other");

        System.out.print("Enter category: ");
        int choice = sc.nextInt();

        Category category;

        switch (choice) {

            case 1:
                category = Category.FOOD;
                break;

            case 2:
                category = Category.TRAVEL;
                break;

            case 3:
                category = Category.SHOPPING;
                break;

            case 4:
                category = Category.BILLS;
                break;

            case 5:
                category = Category.OTHER;
                break;

            default:
                System.out.println("Invalid category");
                return;
        }

        Expense expense = new Expense(id, name, amount, category);

        expenses.add(expense);

        System.out.println("Expense added successfully");
    }

    public static void displayExpenses() {

        if (expenses.size() == 0) {
            System.out.println("No expenses found");
            return;
        }

        System.out.println("\n===== EXPENSE DETAILS =====");

        for (Expense expense : expenses) {
            expense.display();
        }
    }

    public static void deleteExpense() {

        System.out.print("\nEnter expense id: ");
        int id = sc.nextInt();

        boolean found = false;

        for (int i = 0; i < expenses.size(); i++) {

            if (expenses.get(i).id == id) {

                expenses.remove(i);

                found = true;

                System.out.println("Expense deleted successfully");

                break;
            }
        }

        if (found == false) {
            System.out.println("Expense not found");
        }
    }

    public static void totalExpense() {

        double total = 0;

        for (Expense expense : expenses) {
            total = total + expense.amount;
        }

        System.out.println("\nTotal Expense = Rs." + total);
    }

    public static void highestExpense() {

        if (expenses.size() == 0) {
            System.out.println("No expenses found");
            return;
        }

        Expense highest = expenses.get(0);

        for (Expense expense : expenses) {

            if (expense.amount > highest.amount) {
                highest = expense;
            }
        }

        System.out.println("\n===== HIGHEST EXPENSE =====");

        highest.display();
    }

    public static void categoryWiseExpense() {

        HashMap<Category, Double> map = new HashMap<>();

        for (Expense expense : expenses) {

            if (map.containsKey(expense.category)) {

                double oldAmount = map.get(expense.category);

                map.put(
                        expense.category,
                        oldAmount + expense.amount
                );

            } else {

                map.put(
                        expense.category,
                        expense.amount
                );
            }
        }

        System.out.println("\n===== CATEGORY WISE EXPENSE =====");

        for (Category category : map.keySet()) {

            System.out.println(
                    category + " = Rs." + map.get(category)
            );
        }
    }

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==============================");
            System.out.println("   PERSONAL EXPENSE TRACKER");
            System.out.println("==============================");

            System.out.println("1. Add Expense");
            System.out.println("2. Display Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Total Expense");
            System.out.println("5. Highest Expense");
            System.out.println("6. Category Wise Expense");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addExpense();
                    break;

                case 2:
                    displayExpenses();
                    break;

                case 3:
                    deleteExpense();
                    break;

                case 4:
                    totalExpense();
                    break;

                case 5:
                    highestExpense();
                    break;

                case 6:
                    categoryWiseExpense();
                    break;

                case 7:
                    System.out.println("Thank you");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}