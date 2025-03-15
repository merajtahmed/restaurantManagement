import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        assertEquals(3, menu.size(), "Menu should have 3 items.");
        assertEquals("Pizza", menu.get(0).getName());
        assertEquals("Burger", menu.get(1).getName());
        assertEquals("Pasta", menu.get(2).getName());
    }

    @Test
    void testOrderPlacement() {
        Customer customer = new Customer("Alice");
        FoodItem foodItem = foodManager.getFoodItem(1); // Burger

        orderManager.placeOrder(customer, foodItem);
        ArrayList<String> orders = orderManager.getOrders();

        assertEquals(1, orders.size(), "One order should be placed.");
        assertEquals("Alice ordered Burger - $5.99", orders.get(0));
    }

    @Test
    void testInvalidOrder() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            foodManager.getFoodItem(10); // Invalid index
        });
    }
}
