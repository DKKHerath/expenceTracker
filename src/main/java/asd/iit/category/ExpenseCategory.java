package asd.iit.category;

import asd.iit.budget.Budget;

public class ExpenseCategory extends TransactionCategory {
    Budget budget;

    public ExpenseCategory(TransactionType type, String name, String iconUrl, Budget budget) {
        super(type, name, iconUrl);
        this.budget = budget;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
