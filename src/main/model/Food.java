package model;


//information about food, split into different categories of foods, and breakfast lunch and dinner. foods can be
//removed and added only during the day amount is in grams


public class Food {

    private static String GRAINS = "Grains";
    private static String PROTEINS = "Protein";
    private static String VEGETABLE_FRUITS = "Vegetable and Fruits";
    private static String OTHER = "Other";

    private FoodTypes type;
    private int calories;
    private int mass;

    public Food(FoodTypes type, int calories, int mass) {
        this.type = type;
        this.calories = calories;
        this.mass = mass;
    }

    public FoodTypes getType() {
        return type;
    }

    public int getCalories() {
        return calories;
    }

    public int getMass() {
        return mass;
    }

    public void setType(FoodTypes type) {
        this.type = type;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }
}
