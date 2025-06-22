package v_and_v.lab2;

import java.util.*;

public class Order {
    private Customer customer;
    private Map<FoodItem, Integer> items;
    private boolean isPlaced;

    public Order(Customer customer) {
        this.customer = customer;
        this.items = new HashMap<>();
        this.isPlaced = false;
    }

    public void addItem(FoodItem item, int quantity) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Invalid quantity");
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void placeOrder() {
        if (items.isEmpty())
            throw new IllegalStateException("Order must have items");
        this.isPlaced = true;
    }

    public void cancelOrder() {
        if (!isPlaced)
            throw new IllegalStateException("Order not placed");
        this.isPlaced = false;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public Map<FoodItem, Integer> getItems() {
        return items;
    }
}
