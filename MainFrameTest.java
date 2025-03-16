import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

public class MainFrameTest {
    private FoodManager foodManager;
    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        foodManager = new FoodManager();
        orderManager = new OrderManager();

        // Adding test food items
        foodManager.addFoodItem(new FoodItem("Pizza", 8.99));
        foodManager.addFoodItem(new FoodItem("Burger", 5.99));
        foodManager.addFoodItem(new FoodItem("Pasta", 7.49));
    }

    @Test
    void testMenuItems() {
        ArrayList<FoodItem> menu = foodManager.getFoodMenu();

        // Check menu size
        assertEquals(3, menu.size(), "Menu should have exactly 3 items.");

        // Check individual food items
        assertEquals("Pizza", menu.get(0).getName(), "First item should be Pizza.");
        assertEquals("Burger", menu.get(1).getName(), "Second item should be Burger.");
        assertEquals("Pasta", menu.get(2).getName(), "Third item should be Pasta.");

        // Check if the menu contains "Pizza" using assertTrue
        assertTrue(menu.stream().anyMatch(item -> item.getName().equals("Pizza")), "Menu should contain Pizza.");
    }

    @Test
    void testOrderPlacement() {
        Customer customer = new Customer("Alice");
        FoodItem foodItem = foodManager.getFoodItem(1); // Burger

        orderManager.placeOrder(customer, foodItem);
        ArrayList<String> orders = orderManager.getOrders();

        assertEquals(1, orders.size(), "One order should be placed.");

        assertFalse(orders.isEmpty());

    }

    @Test
    void testEmptyOrderList() {
        ArrayList<String> orders = orderManager.getOrders();

        // Using assertTrue to check if order list is empty initially
        assertTrue(orders.isEmpty(), "Order list should be empty before placing any orders.");

        Customer customer = new Customer("Bob");
        orderManager.placeOrder(customer, foodManager.getFoodItem(2)); // Pasta

        // Using assertFalse to check order list is no longer empty
        assertFalse(orders.isEmpty(), "Order list should not be empty after placing an order.");
    }

    @Test
    void testAssertNullAndNotNull() {
        // Test assertNotNull for a valid food item
        FoodItem validFood = foodManager.getFoodItem(1); // Burger
        assertNotNull(validFood, "Valid food item should not be null.");

        // Test assertNull for an invalid food index
        FoodItem invalidFood = null;
        try {
            invalidFood = foodManager.getFoodItem(10); // Out of bounds
        } catch (IndexOutOfBoundsException ignored) {}

        assertNull(invalidFood, "Invalid food index should return null.");
    }

    @Test
    void testTimeout() {
        // Simulating a method that takes time and we can test with timeout
        assertTimeout(java.time.Duration.ofMillis(100), () -> {
            // Simulate a method that executes within 100 milliseconds
            Thread.sleep(50); // Sleep for 50ms to ensure this passes
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pizza", "Burger", "Pasta"})
    void testMenuItemsWithParameterizedTest(String foodName) {
        // Check if the menu contains the food item
        ArrayList<FoodItem> menu = foodManager.getFoodMenu();
        assertTrue(menu.stream().anyMatch(item -> item.getName().equals(foodName)),
                "Menu should contain " + foodName + ".");
    }

}
