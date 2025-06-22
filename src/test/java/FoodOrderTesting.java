import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import v_and_v.lab2.Customer;
import v_and_v.lab2.FoodItem;
import v_and_v.lab2.Order;
import v_and_v.lab2.OrderUtils;

public class FoodOrderTesting {

    FoodItem tea;
    Customer customer;
    Order order;

    @BeforeEach
    void setUp() {
        tea = new FoodItem("Tea", 5);
        customer = new Customer("Nixon");
        order = new Order(customer);
    }

    @Test
    // Add Food Item to Order
    void addOrder() {
        order.addItem(tea, 1);
        Map<FoodItem, Integer> itemList = new HashMap<>();
        itemList.put(tea, 1);
        assertEquals(itemList, order.getItems());
    }

    @Test
    // Place an Order Successfully
    void placeOrder() {
        order.addItem(tea, 1);
        assertFalse(order.isPlaced());
        order.placeOrder();
        assertTrue(order.isPlaced());
    }

    @Test
    // Place Empty Order
    void placeEmptyOrder() {
        assertEquals(0, order.getItems().size());
        IllegalStateException exception = assertThrows(IllegalStateException.class, order::placeOrder);
        assertEquals("Order must have items", exception.getMessage());
    }

    @Test
    // Cancel a Placed Order
    void cancelOrder() {
        order.addItem(tea, 1);
        assertFalse(order.isPlaced());
        order.placeOrder();
        assertTrue(order.isPlaced());
        order.cancelOrder();
        assertFalse(order.isPlaced());
    }

    @Test
    // Cancel a Non-Placed Order
    void cancelNonPlacedOrder() {
        assertFalse(order.isPlaced());
        IllegalStateException exception = assertThrows(IllegalStateException.class, order::cancelOrder);
        assertEquals("Order not placed", exception.getMessage());
    }

    @Test
    // Calculate Total Order Price
    void calculateTotal() {
        order.addItem(tea, 1);
        assertEquals(tea.getPrice() * 1, OrderUtils.calculateTotal(order));
    }

    @Test
    // Add Same Item Twice
    void addItemTwice() {
        order.addItem(tea, 1);
        Map<FoodItem, Integer> itemList = new HashMap<>();
        itemList.put(tea, 1);
        assertEquals(itemList, order.getItems());
        itemList.clear();
        itemList.put(tea, 2);
        order.addItem(tea, 1);
        assertEquals(itemList, order.getItems());
    }
}
