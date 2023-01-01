package asd.iit.category;

public class CategoryModel {
    String type;
    String name;
    String iconUrl;
    String budget;

    public CategoryModel(String type, String name, String iconUrl, String budget) {
        this.type = type;
        this.name = name;
        this.iconUrl = iconUrl;
        this.budget = budget;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
