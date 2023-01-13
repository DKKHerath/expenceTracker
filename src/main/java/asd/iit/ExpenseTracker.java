package asd.iit;

import asd.iit.budget.Budget;
import asd.iit.category.TransactionCategory;
import asd.iit.transaction.RecurrentType;
import asd.iit.transaction.Transaction;
import asd.iit.transaction.TransactionModelUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ExpenseTracker {

    void createTransaction(String title, double amount, String category, String desc, LocalDateTime dateTime, RecurrentType recurrentType);

    ArrayList<Transaction> printAllTransactions();

    //3. Allow the user to edit/delete transactions
    void deleteTransaction(String trnID);

    //3.1. Allow the user to edit/delete transactions
    void updateTransaction(TransactionModelUpdate transactionModelUpdate);

    ArrayList<TransactionCategory> printAllCategories();

    //4.1 An advanced application will allow the user to add new categories.
    void createCategory(String type, String name, String iconUrl, String budget);

    void createABudget(String name, Double alertAmount, Double totalBudgetAmount);

    ArrayList<Budget> getAllBudgets();

    List<String> analyseTransactions();

    String calculateOverallBudgetConsumption();
}
