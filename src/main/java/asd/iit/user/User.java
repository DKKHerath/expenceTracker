package asd.iit.user;

import asd.iit.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final UUID userId;
    private String userName;
    private String emailAddress;
    private Currency defaultCurrency;
    ArrayList<Transaction> transactions;

    public User(String userName, String emailAddress, Currency defaultCurrency) {
        this.userId = setUserId();
        this.userName = userName;
        this.emailAddress = emailAddress;
        this.defaultCurrency = defaultCurrency;

        //implementation of composition
        this.transactions=new ArrayList<>();
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

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }
}
