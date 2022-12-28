package asd.iit;

import asd.iit.budget.Budget;
import asd.iit.category.IncomeCategory;
import asd.iit.category.TransactionCategory;
import asd.iit.category.TransactionType;
import asd.iit.transaction.Expense;
import asd.iit.transaction.Income;
import asd.iit.transaction.RecurrentType;
import asd.iit.transaction.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseTrackerImpl implements ExpenseTracker {


    ArrayList<Transaction> transactions;
    ArrayList<Budget> budgets;
    ArrayList<TransactionCategory> transactionCategories;

    public ExpenseTrackerImpl() {
        this.transactions = new ArrayList<>();
        this.budgets = new ArrayList<>();
        this.transactionCategories = new ArrayList<>();

        //4. Allow the user to see a list of categories. The application should come with some preset categories.
        transactionCategories.add(new IncomeCategory(TransactionType.INCOME, "Salary", "null"));
        transactionCategories.add(new IncomeCategory(TransactionType.INCOME, "Profit", "null"));
        budgets.add(new Budget("TEST", 100d, 500d));
    }

    //1. Allow a user to see a list of recent transactions
    @Override
    public ArrayList<Transaction> printAllTransactions() {
        return transactions;
    }


    //2. Allow a user to enter a new transaction. At a minimum, this should include amount, whether it is an income or
    // expense transaction and which category it falls under. A more advanced application will allow the user to enter a
    // note and to specify whether it is recurring)
    @Override
    public void saveTransaction(String title, double amount, String category, String desc, LocalDateTime dateTime, RecurrentType recurrentType) {
        boolean categoryTypeAvailable = false;
        LocalDateTime dateTime1 = null;
        TransactionType transactionType = null;
        TransactionCategory transactionCategory = null;
        RecurrentType recurrentType1 = null;

        if (title.isEmpty()) {
            System.out.println("ERROR1: Please provide a valid title.");
            return;
        }

        if (amount <= 0) {
            System.out.println("ERROR2: Please provide a valid amount.");
            return;
        }


        if (dateTime == null) {
            dateTime1 = LocalDateTime.now();
        } else {
            dateTime1 = dateTime;
        }

        if (recurrentType == null) {
            recurrentType1 = RecurrentType.DAILY;
        } else {
            recurrentType1 = recurrentType;
        }


        if (category.isEmpty()) {
            System.out.println("ERROR3: Please provide a valid category.");
            return;
        } else {
            for (TransactionCategory tc : transactionCategories) {
                if (tc.getName().equals(category)) {
                    categoryTypeAvailable = true;
                    transactionCategory = tc;
                }

            }
        }

        if (categoryTypeAvailable) {
            if (transactionCategory.getType() == TransactionType.EXPENSE) {
                //create an Expense
                transactions.add(new Expense(title, amount, transactionCategory, desc, dateTime1, recurrentType1));
                System.out.println("SUCCESS_EXPENSE: The Record saved.");
            } else {
                //create an Income
                transactions.add(new Income(title, amount, transactionCategory, desc, dateTime1, recurrentType1));
                System.out.println("SUCCESS_INCOME: The Record saved.");

            }
        } else {
            System.out.println("ERROR4: The category has not saved in the system.");
        }


    }


    //3. Allow the user to edit/delete transactions
    //TOBE DONE


    //4. Allow the user to see a list of categories. The application should come with some preset categories.


    @Override
    public ArrayList<TransactionCategory> printAllCategories() {
        return transactionCategories;
    }

    //4.1 An advanced application will allow the user to add new categories.
    //TOBE DONE


    //5. Allow the user to enter a budget, specifying amounts for each category.

    @Override
    public void createABudget(String name, Double alertAmount, Double totalBudgetAmount) {

        if (name.isEmpty()) {
            System.out.println("ERROR: Please provide a name.");
            return;
        }

        if (alertAmount <= 0) {
            System.out.println("ERROR: Please provide a valid alert amount.");
            return;
        }

        if (totalBudgetAmount <= 0) {
            System.out.println("ERROR: Please provide a valid budget amount.");
            return;
        }

        budgets.add(new Budget(name, alertAmount, totalBudgetAmount));
        System.out.println("SUCCESS_NEW_BUDGET: Record saved.");
    }

    @Override
    public ArrayList<Budget> getAllBudgets() {
        return budgets;
    }


    //6. Allow the user to track their progress against their budget by seeing how much they have spent in each category
    // against the budget for that category, as well as overall spending against overall budget.
    @Override
    public List<String> analyseTransactions() {

        List<String> output = new ArrayList<>();
        Map<String, Double> budgetUsage = new HashMap<>();
        String budgetName = "";

        //Calculating transactions expenses for each category
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().getType() == TransactionType.EXPENSE) {
                budgetName = transaction.getCategory().getName();
                if (budgetUsage.containsKey(budgetName)) {
                    budgetUsage.put(budgetName, budgetUsage.get(budgetName) + transaction.getAmount());
                } else {
                    budgetUsage.put(budgetName, transaction.getAmount());
                }

            }

        }

        //Check against the overall budget
        for (Budget bgt : budgets) {

            if (budgetUsage.containsKey(bgt.getName())) {
                output.add(bgt.toString());

                //System.out.println("\nBudget Name: " + bgt.getName());
                //System.out.println("Budget Amount: " + bgt.getTotalBudgetAmount());
                //System.out.println("Budget Usage: " + budgetUsage.get(bgt.getName()));
                //System.out.println("Budget Remaining: " + (bgt.getTotalBudgetAmount() - budgetUsage.get(bgt.getName())));
            }


        }
        return output;

    }


}

