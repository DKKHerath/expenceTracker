package asd.iit.user;

import asd.iit.budget.Budget;
import asd.iit.category.TransactionCategory;
import asd.iit.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID userId;
    private ArrayList<Transaction> transactions;
    private ArrayList<Budget> budgets;
    private ArrayList<TransactionCategory> transactionCategories;
    private String userName;
    private String emailAddress;
    private String password;

    public User(String userName, String emailAddress, String password) {
        this.userId = setUserId();
        this.userName = userName;
        this.emailAddress = emailAddress;

        //implementation of composition
        this.transactions = new ArrayList<>();
        this.budgets = new ArrayList<>();
        this.transactionCategories = new ArrayList<>();
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID setUserId() {
        return UUID.randomUUID();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Budget> getBudgets() {
        return budgets;
    }

    public void setBudgets(Budget budget) {
        this.budgets.add(budget);
    }

    public ArrayList<TransactionCategory> getTransactionCategories() {
        return transactionCategories;
    }

    public void setTransactionCategories(TransactionCategory transactionCategorie) {
        this.transactionCategories.add(transactionCategorie);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void setTransactions(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public void deleteTransaction(Transaction transaction) {
        this.transactions.remove(transaction);
    }
}
