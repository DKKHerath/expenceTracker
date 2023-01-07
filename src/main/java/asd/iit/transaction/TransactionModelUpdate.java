package asd.iit.transaction;

import java.time.LocalDateTime;

public class TransactionModelUpdate {
    String id;
    String title;
    Double amount;
    String desc;
    String dateTime;
    RecurrentType recurrentType;

    public TransactionModelUpdate(String id, String title, Double amount, String category, String desc, String dateTime, RecurrentType recurrentType) {
        this.id = id;
        this.title = title;
        this.amount = amount;
        this.desc = desc;
        this.dateTime = dateTime;
        this.recurrentType = recurrentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(dateTime);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public RecurrentType getRecurrentType() {
        return recurrentType;
    }

    public void setRecurrentType(RecurrentType recurrentType) {
        this.recurrentType = recurrentType;
    }
}
