# Question 1: Use selenium, Time 1h, Marks 50

### Test Case 1: Register User

1. Launch browser
2. Navigate to url http://automationexercise.com
3. Click on 'Signup / Login' button
4. Enter name and email address
5. Click 'Signup' button
6. Verify that 'ENTER ACCOUNT INFORMATION' is visible
7. Fill details: Title, Name, Email, Password, Date of birth
8. Select checkbox 'Sign up for our newsletter!'
9. Select checkbox 'Receive special offers from our partners!'
10. Fill details: First name, Last name, Company, Address, Address2, Country, State, City,
    Zip Code, Mobile Number
11. Click 'Create Account button'
12. Verify that 'ACCOUNT CREATED!' is visible
13. Click 'Continue' button
14. Verify that 'Logged in as username' is visible
15. Click 'Logout' button, verify logout is successful

### Test Case 2: Search Products and Verify Cart After Login

1. Launch browser
2. Navigate to url http://automationexercise.com
3. Click on 'Products' button
4. Verify user is navigated to ALL PRODUCTS page successfully
5. Enter product name in search input and click search button
6. Verify 'SEARCHED PRODUCTS' is visible
7. Verify all the products related to search are visible
8. Add those products to cart
9. Click 'Cart' button and verify that products are visible in cart
10. Click 'Signup / Login' button and submit login details
11. Again, go to Cart page
12. Verify that those products are visible in cart after login as well

### Test Case 3: Add review on product

1. Launch browser
2. Navigate to url http://automationexercise.com
3. Click on 'Products' button
4. Verify user is navigated to ALL PRODUCTS page successfully
5. Click on 'View Product' button
6. Verify 'Write Your Review' is visible
7. Enter name, email and review
8. Click 'Submit' button
9. Verify the success message 'Thank you for your review.'

# Question 2: Time 1h, Marks 50

**Project Theme:** Online Food Ordering System

You are building a basic online food ordering system. It should allow food items to be added to
an order, track quantities, calculate total prices, and handle order status changes. Customers can
place and cancel orders, and the system ensures constraints like valid quantities and total limits.

**Java Project Files Overview:**  
`FoodItem.java`:

```
public class FoodItem {
    private String name;
    private double price;

    public FoodItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
```

`Customer.java`:

```
public class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```

`Order.java`:

```
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
```

`OrderUtils.java`:

```
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
```

### Test cases:

1. Add Food Item to Order: Test that adding a food item with a valid quantity stores it in the
   order correctly.
2. Place an Order Successfully: Test that calling placeOrder() sets isPlaced to true for a
   non-empty order.
3. Place Empty Order:Test that placing an empty order throws IllegalStateException.
4. Cancel a Placed Order: Test that cancelling a placed order sets isPlaced to false.
5. Cancel a Non-Placed Order: Test that cancelling an order that hasn’t been placed throws
   IllegalStateException.
6. Calculate Total Order Price: Test that OrderUtils.calculateTotal() returns the correct total
   price based on quantities and item prices.
7. Add Same Item Twice: Test that adding the same item multiple times correctly increases
   the quantity.
