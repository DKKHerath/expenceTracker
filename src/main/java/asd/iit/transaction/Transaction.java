package asd.iit.transaction;

import asd.iit.category.TransactionCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Transaction {
    private final UUID trnId;
    private String title;
    private Double amount;
    private TransactionCategory category;
    private String description;
    private LocalDateTime date;
    private RecurrentType recurrentType;

    public Transaction(String title, Double amount, TransactionCategory category, String description, LocalDateTime date) {
        this.trnId = setTrnId();
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
        this.recurrentType = recurrentType;
    }


    public UUID getTrnId() {
        return trnId;
    }

    public UUID setTrnId() {
        return UUID.randomUUID();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionCategory getCategory() {
        return category;
    }

    public void setCategory(TransactionCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public RecurrentType getRecurrentType() {
        return recurrentType;
    }

    public void setRecurrentType(RecurrentType recurrentType) {
        this.recurrentType = recurrentType;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                ", title='" + title + '\'' +
                ", amount=" + amount +
                ", category=" + category.getType() +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", recurrentType=" + recurrentType +
                '}';
    }
}
