package asd.iit;

import asd.iit.budget.Budget;
import asd.iit.category.TransactionCategory;
import asd.iit.transaction.RecurrentType;
import asd.iit.transaction.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface ExpenseTracker {

    void saveTransaction(String title, double amount, String category, String desc, LocalDateTime dateTime, RecurrentType recurrentType);

    ArrayList<Transaction> printAllTransactions();

    ArrayList<TransactionCategory> printAllCategories();

    void createABudget(String name, Double alertAmount, Double totalBudgetAmount);

    ArrayList<Budget> getAllBudgets();

    List<String> analyseTransactions();

}
