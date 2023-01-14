package asd.iit.category;

import java.util.UUID;

public abstract class TransactionCategory {
    private final UUID trsId;
    private TransactionType type;
    private String name;
    private String iconUrl;

    public TransactionCategory(TransactionType type, String name, String iconUrl) {
        this.trsId = setTrsId();
        this.type = type;
        this.name = name;
        this.iconUrl = iconUrl;
    }


    public UUID getTrsId() {
        return trsId;
    }

    public UUID setTrsId() {
        return UUID.randomUUID();
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
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

    @Override
    public String toString() {
        return "TransactionCategory{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
