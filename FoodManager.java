
import java.util.ArrayList;

// FoodManager.java - Manages food items
class FoodManager {

    private ArrayList<FoodItem> foodMenu = new ArrayList<>();

    public void addFoodItem(FoodItem item) {
        foodMenu.add(item);
    }

    public ArrayList<FoodItem> getFoodMenu() {
        return foodMenu;
    }

    public FoodItem getFoodItem(int index) {
        if (index >= 0 && index < foodMenu.size()) {
            return foodMenu.get(index);
        }
        return null;
    }
}
