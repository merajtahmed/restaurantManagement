
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// ... (FoodItem, Customer, FoodManager, OrderManager classes remain the same)
// MainFrame.java - Main UI Controller
public class MainFrame {

    private FoodManager foodManager;
    private OrderManager orderManager;
    private JTextArea displayArea;
    private JTextField customerNameField;
    private JTextField foodChoiceField;

    public MainFrame () {
        foodManager = new FoodManager();
        orderManager = new OrderManager();

        // Adding some food items
        foodManager.addFoodItem(new FoodItem("Pizza", 8.99));
        foodManager.addFoodItem(new FoodItem("Burger", 5.99));
        foodManager.addFoodItem(new FoodItem("Pasta", 7.49));

        JFrame frame = new JFrame("Restaurant Management System");
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel customerLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField();
        JLabel foodChoiceLabel = new JLabel("Food Item Number:");
        foodChoiceField = new JTextField();

        JButton viewMenuButton = new JButton("View Menu");
        JButton placeOrderButton = new JButton("Place Order");
        JButton viewOrdersButton = new JButton("View Orders");
        JButton exitButton = new JButton("Exit");

        inputPanel.add(customerLabel);
        inputPanel.add(customerNameField);
        inputPanel.add(foodChoiceLabel);
        inputPanel.add(foodChoiceField);
        inputPanel.add(viewMenuButton);
        inputPanel.add(placeOrderButton);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewOrdersButton);
        buttonPanel.add(exitButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        viewMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMenu();
            }
        });

        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });

        viewOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewOrders();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }

    private void displayMenu() {
        displayArea.setText("");
        displayArea.append("--- Food Menu ---\n");
        ArrayList<FoodItem> menu = foodManager.getFoodMenu();
        for (int i = 0; i < menu.size(); i++) {
            displayArea.append((i + 1) + ". " + menu.get(i) + "\n");
        }
    }

    private void placeOrder() {
        String customerName = customerNameField.getText();
        String foodChoiceStr = foodChoiceField.getText();

        try {
            int foodChoice = Integer.parseInt(foodChoiceStr) - 1;
            Customer customer = new Customer(customerName);
            FoodItem selectedFood = foodManager.getFoodItem(foodChoice);

            if (selectedFood != null) {
                orderManager.placeOrder(customer, selectedFood);
                displayArea.append("Order placed successfully!\n");
            } else {
                displayArea.append("Invalid choice!\n");
            }
        } catch (NumberFormatException e) {
            displayArea.append("Invalid food item number!\n");
        }
    }

    private void viewOrders() {
        displayArea.setText("");
        displayArea.append("--- All Orders ---\n");
        ArrayList<String> orders = orderManager.getOrders();
        if (orders.isEmpty()) {
            displayArea.append("No orders placed yet.\n");
        } else {
            for (String order : orders) {
                displayArea.append(order + "\n");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame (); // Corrected line
            }
        });
    }
}
