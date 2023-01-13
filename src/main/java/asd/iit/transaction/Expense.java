package asd.iit.transaction;

import asd.iit.category.TransactionCategory;

import java.time.LocalDateTime;

public class Expense extends Transaction {

    public Expense(String title, Double amount, TransactionCategory category, String description, LocalDateTime date ) {
        super(title, amount, category, description, date);
    }
}

