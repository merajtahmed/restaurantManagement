import java.util.ArrayList;

// OrderManager.java - Manages customer orders
class OrderManager {
    private ArrayList<String> orders = new ArrayList<>();

    public void placeOrder(Customer customer, FoodItem item) {
        orders.add(customer.getName() + " ordered " + item.getName());
    }

    public ArrayList<String> getOrders() {
        return orders;
    }
}