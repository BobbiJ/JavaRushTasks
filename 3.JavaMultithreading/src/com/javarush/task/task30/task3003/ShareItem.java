package com.javarush.task.task30.task3003;

//This class shows how to call other constructors using 'this'
public class ShareItem {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShareItem shareItem = (ShareItem) o;

        if (itemId != shareItem.itemId) return false;
        return description.equals(shareItem.description);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + itemId;
        return result;
    }

    @Override
    public String toString() {
        return "ShareItem{" +
                "description='" + description + '\'' +
                ", itemId=" + itemId +
                '}';
    }

    public String description;
    public int itemId;

    public ShareItem() {
        this("Test Item", 0);
    }

    public ShareItem(String description) {
        this(description, 0);
    }

    public ShareItem(int itemId) {
        this("Test Item", itemId);
    }

    public ShareItem(String description, int itemId) {
        this.description = description;
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public int getItemId() {
        return itemId;
    }
}