package asd.iit.transaction;

import java.time.LocalDateTime;

public class TransactionModel {
    String title;
    Double amount;
    String category;
    String desc;
    LocalDateTime dateTime;
    RecurrentType recurrentType;

    public TransactionModel(String title, Double amount, String category, String desc, LocalDateTime dateTime, RecurrentType recurrentType) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.desc = desc;
        this.dateTime = dateTime;
        this.recurrentType = recurrentType;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public RecurrentType getRecurrentType() {
        return recurrentType;
    }

    public void setRecurrentType(RecurrentType recurrentType) {
        this.recurrentType = recurrentType;
    }
}
