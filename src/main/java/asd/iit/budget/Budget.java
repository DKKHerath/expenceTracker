package asd.iit.budget;

import java.util.UUID;

public class Budget {
    private final UUID id;
    private String name;
    private Double alertAmount;
    private Double totalBudgetAmount;

    public Budget(String name, Double alertAmount, Double totalBudgetAmount) {
        this.id = setId();
        this.name = name;
        this.alertAmount = alertAmount;
        this.totalBudgetAmount = totalBudgetAmount;
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID setId() {
        return UUID.randomUUID();
    }

    public Double getAlertAmount() {
        return alertAmount;
    }

    public void setAlertAmount(Double alertAmount) {
        this.alertAmount = alertAmount;
    }

    public Double getTotalBudgetAmount() {
        return totalBudgetAmount;
    }

    public void setTotalBudgetAmount(Double totalBudgetAmount) {
        this.totalBudgetAmount = totalBudgetAmount;
    }

    @Override
    public String toString() {
        return "Budget{" +
                "name='" + name + '\'' +
                ", alertAmount=" + alertAmount +
                ", totalBudgetAmount=" + totalBudgetAmount +
                '}';
    }
}
