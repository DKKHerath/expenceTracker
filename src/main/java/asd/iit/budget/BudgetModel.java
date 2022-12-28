package asd.iit.budget;

public class BudgetModel {
    private String name;
    private Double alertAmount;
    private Double totalBudgetAmount;

    public BudgetModel(String name, Double alertAmount, Double totalBudgetAmount) {
        this.name = name;
        this.alertAmount = alertAmount;
        this.totalBudgetAmount = totalBudgetAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
