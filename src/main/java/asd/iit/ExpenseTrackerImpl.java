package asd.iit;

import asd.iit.budget.Budget;
import asd.iit.category.ExpenseCategory;
import asd.iit.category.IncomeCategory;
import asd.iit.category.TransactionCategory;
import asd.iit.category.TransactionType;
import asd.iit.transaction.*;
import asd.iit.user.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseTrackerImpl implements ExpenseTracker {


    ArrayList<Budget> budgets;
    ArrayList<TransactionCategory> transactionCategories;
    User loggedInUser;


    public ExpenseTrackerImpl() {
        this.budgets = new ArrayList<>();
        this.transactionCategories = new ArrayList<>();


        //4. Allow the user to see a list of categories. The application should come with some preset categories.

        //Budget
        budgets.add(new Budget("Standard Budget", 1000d, 10000d));

        //INCOME
        transactionCategories.add(new IncomeCategory(TransactionType.INCOME, "Salary", "null"));
        transactionCategories.add(new IncomeCategory(TransactionType.INCOME, "Deposit", "null"));
        transactionCategories.add(new IncomeCategory(TransactionType.INCOME, "Re-Imbursements", "null"));
        transactionCategories.add(new IncomeCategory(TransactionType.INCOME, "Savings", "null"));

        //EXPENSE
        transactionCategories.add(new ExpenseCategory(TransactionType.EXPENSE, "Miscellaneous", "null", budgets.get(0)));

        //hard coded user for the demonstration
        User demoUser1 = new User("DEMOUSER1", "demo1@iit.lk", "password1");
        loggedInUser = demoUser1;


    }

    //1. Allow a user to see a list of recent transactions
    @Override
    public ArrayList<Transaction> printAllTransactions() {
        return loggedInUser.getTransactions();
    }


    //2. Allow a user to enter a new transaction. At a minimum, this should include amount, whether it is an income or
    // expense transaction and which category it falls under. A more advanced application will allow the user to enter a
    // note and to specify whether it is recurring)
    @Override
    public void createTransaction(String title, double amount, String category, String desc, LocalDateTime dateTime, RecurrentType recurrentType) {
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


            //merge default categories with user specific categories
            ArrayList<TransactionCategory> mergedCategories = new ArrayList<>();
            mergedCategories.addAll(transactionCategories);
            mergedCategories.addAll(loggedInUser.getTransactionCategories());

            System.out.println(category);
            for (TransactionCategory tc : mergedCategories) {
                if (tc.getName().equals(category)) {
                    categoryTypeAvailable = true;
                    transactionCategory = tc;
                }

            }
        }

        if (categoryTypeAvailable) {
            if (transactionCategory.getType() == TransactionType.EXPENSE) {
                //create an Expense
                Expense newExpense = new Expense(title, amount, transactionCategory, desc, dateTime1);
                newExpense.setRecurrentType(recurrentType1);
                loggedInUser.setTransactions(newExpense);

                System.out.println("SUCCESS_EXPENSE: The Record saved.");
            } else {
                //create an Income
                Income newIncome = new Income(title, amount, transactionCategory, desc, dateTime1);
                newIncome.setRecurrentType(recurrentType1);
                loggedInUser.setTransactions(newIncome);
                System.out.println("SUCCESS_INCOME: The Record saved.");

            }
        } else {
            System.out.println("ERROR4: The category has not saved in the system.");
        }


    }


    //3. Allow the user to edit/delete transactions
    @Override
    public void deleteTransaction(String trnID) {
        int index = -1;
        for (Transaction transaction : loggedInUser.getTransactions()) {
            if (transaction.getTrnId().toString().equals(trnID)) {
                loggedInUser.deleteTransaction(transaction);
                System.out.println("SUCCESS_TRANSACTION_DELETE: " + transaction.getTitle());
                break;
            }

        }
    }


    //3.1. Allow the user to edit/delete transactions
    @Override
    public void updateTransaction(TransactionModelUpdate transactionModelUpdate) {
        int index = -1;
        for (Transaction transaction : loggedInUser.getTransactions()) {
            if (transaction.getTrnId().toString().equals(transactionModelUpdate.getId())) {
                transaction.setTitle(transactionModelUpdate.getTitle());
                transaction.setAmount(transactionModelUpdate.getAmount());
                transaction.setDescription(transactionModelUpdate.getDesc());
                transaction.setDate(transactionModelUpdate.getDateTime());
                transaction.setRecurrentType(transactionModelUpdate.getRecurrentType());
                System.out.println("SUCCESS_TRANSACTION_EDIT: " + transaction.getTitle());
                break;
            }

        }
    }

    //4. Allow the user to see a list of categories. The application should come with some preset categories.


    @Override
    public ArrayList<TransactionCategory> printAllCategories() {
        //merge default categories with user specific categories
        ArrayList<TransactionCategory> mergedCategories = new ArrayList<>();
        mergedCategories.addAll(transactionCategories);
        mergedCategories.addAll(loggedInUser.getTransactionCategories());

        return mergedCategories;
    }

    //4.1 An advanced application will allow the user to add new categories.
    @Override
    public void createCategory(String type, String name, String iconUrl, String budget) {

        Budget budgetExist = null;

        if (type.equals(TransactionType.EXPENSE.toString())) {

            if (!budget.isEmpty()) {

                //merge default budgets with user specific budgets
                ArrayList<Budget> mergedBudgets = new ArrayList<>();
                mergedBudgets.addAll(budgets);
                mergedBudgets.addAll(loggedInUser.getBudgets());

                for (Budget bgt : mergedBudgets) {
                    if (bgt.getName().equals(budget)) {
                        budgetExist = bgt;
                        break;
                    }

                }
            }

            if (budgetExist == null) {
                System.out.println("ERROR: Budget Not Exist.");
                return;
            }

            loggedInUser.setTransactionCategories(new ExpenseCategory(TransactionType.EXPENSE, name, iconUrl, budgetExist));
            System.out.println("SUCCESS_BUDGET_EXPENSE: Record Saved.");
        } else {
            loggedInUser.setTransactionCategories(new IncomeCategory(TransactionType.INCOME, name, iconUrl));
            System.out.println("SUCCESS_BUDGET_INCOME: Record Saved.");
        }

    }

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

        loggedInUser.setBudgets(new Budget(name, alertAmount, totalBudgetAmount));
        System.out.println("SUCCESS_NEW_BUDGET: Record saved.");
    }

    @Override
    public ArrayList<Budget> getAllBudgets() {
        //merge default budgets with user specific budgets
        ArrayList<Budget> mergedBudgets = new ArrayList<>();
        mergedBudgets.addAll(budgets);
        mergedBudgets.addAll(loggedInUser.getBudgets());

        return mergedBudgets;
    }

    //6. Allow the user to track their progress against their budget by seeing how much they have spent in each category
    // against the budget for that category, as well as overall spending against overall budget.
    @Override
    public List<String> analyseTransactions() {

        List<String> output = new ArrayList<>();
        Map<String, Double> budgetUsage = new HashMap<>();
        String budgetName = "";


        for (Transaction transaction : loggedInUser.getTransactions()) {
            System.out.println(transaction.toString());
        }

        //Calculating transactions expenses for each category
        for (Transaction transaction : loggedInUser.getTransactions()) {
            if (transaction.getCategory().getType() == TransactionType.EXPENSE) {
                ExpenseCategory expenseCategory = (ExpenseCategory) transaction.getCategory();

                budgetName = expenseCategory.getBudget().getName();
                if (budgetUsage.containsKey(budgetName)) {
                    budgetUsage.put(budgetName, (budgetUsage.get(budgetName) + transaction.getAmount()));
                } else {
                    budgetUsage.put(budgetName, transaction.getAmount());
                }

            }

        }

        //Check against the overall budget
        //merge default budgets with user specific budgets
        ArrayList<Budget> mergedBudgets = new ArrayList<>();
        mergedBudgets.addAll(budgets);
        mergedBudgets.addAll(loggedInUser.getBudgets());
        for (Budget bgt : mergedBudgets) {

            if (budgetUsage.containsKey(bgt.getName())) {
                output.add("Budget Name:" + bgt.getName() +
                        " Budget Usage: " + budgetUsage.get(bgt.getName()) +
                        " Budget Remaining: " + (bgt.getTotalBudgetAmount() - budgetUsage.get(bgt.getName())));

                System.out.println("\nBudget Name: " + bgt.getName());
                System.out.println("Budget Amount: " + bgt.getTotalBudgetAmount());
                System.out.println("Budget Usage: " + budgetUsage.get(bgt.getName()));
                System.out.println("Budget Remaining: " + (bgt.getTotalBudgetAmount() - budgetUsage.get(bgt.getName())));
            }


        }
        return output;

    }

    @Override
    public String calculateOverallBudgetConsumption() {

        String output = "";
        Double totalBudget = 0d, usedBudget = 0d;
        Map<String, Double> budgetUsage = new HashMap<>();
        String budgetName = "";

        //Calculating transactions expenses for each category
        for (Transaction transaction : loggedInUser.getTransactions()) {
            if (transaction.getCategory().getType() == TransactionType.EXPENSE) {
                ExpenseCategory expenseCategory = (ExpenseCategory) transaction.getCategory();

                budgetName = expenseCategory.getBudget().getName();
                if (budgetUsage.containsKey(budgetName)) {
                    budgetUsage.put(budgetName, (budgetUsage.get(budgetName) + transaction.getAmount()));
                } else {
                    budgetUsage.put(budgetName, transaction.getAmount());
                }

            }

        }

        //Check the overall budget

        //merge default budgets with user specific budgets
        ArrayList<Budget> mergedBudgets = new ArrayList<>();
        mergedBudgets.addAll(budgets);
        mergedBudgets.addAll(loggedInUser.getBudgets());
        for (Budget bgt : mergedBudgets) {
            totalBudget += bgt.getTotalBudgetAmount();

            if (budgetUsage.containsKey(bgt.getName())) {
                usedBudget += budgetUsage.get(bgt.getName());

            }


        }

        output = "Total Budget: " + totalBudget + " Used Amount: " + usedBudget;
        return output;
    }


}

