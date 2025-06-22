package v_and_v.lab2;

import java.util.Map;

public class OrderUtils {
    public static double calculateTotal(Order order) {
        double total = 0;
        for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
