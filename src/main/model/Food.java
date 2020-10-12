package model;


//information about food, split into different categories of foods, and breakfast lunch and dinner. foods can be
//removed and added only during the day amount is in grams


public class Food {

    private String name;
    private FoodTypes type;
    private int calories;
    private int mass;
    private String timeOfDay;

    public Food() {
        this.name = "";
        this.type = null;
        this.calories = 0;
        this.mass = 0;
        this.timeOfDay = "";
    }


    //EFFECTS: returns string that represents the foods type
    public String typeToString() {
        FoodTypes type = this.getType();
        String s = null;
        if (type == FoodTypes.GRAINS) {
            s = "Grains";
        } else if (type == FoodTypes.PROTEINS) {
            s = "Proteins";
        } else if (type == FoodTypes.VEGETABLES_AND_FRUITS) {
            s = "Vegetables and Fruits";
        } else if (type == FoodTypes.OTHERS) {
            s = "Others";
        }
        return s;
    }

    //Getters and Setters
    public FoodTypes getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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

    public String getTimeOfDay() {
        return this.timeOfDay;
    }

    public void setTimeOfDay(String s) {
        this.timeOfDay = s;
    }
}
