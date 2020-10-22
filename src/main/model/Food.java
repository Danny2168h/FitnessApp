package model;


//Represents a food, with information about the foods name, amount of calories, mass and when it was consumed.
//Mass is in grams.

import org.json.JSONObject;
import persistence.Writeable;

public class Food implements Writeable {

    private String name;
    private FoodTypes type;
    private int calories;
    private double mass;
    private String timeOfDay;


    //EFFECTS: Creates food with double and int fields set to 0 and string fields null(main constructor used in program)
    public Food() {
    }

    //EFFECTS: constructor that sets value of all fields for a food object
    // Used in tests to improve readability
    public Food(String name, FoodTypes type, int calories, double mass, String timeOfDay) {
        this.name = name;
        this.type = type;
        this.calories = calories;
        this.mass = mass;
        this.timeOfDay = timeOfDay;
    }

    //EFFECTS: returns string that represents the foods type
    public String typeToString() {
        FoodTypes type = this.getType();
        String s;
        if (type == FoodTypes.GRAINS) {
            s = "Grains";
        } else if (type == FoodTypes.PROTEINS) {
            s = "Proteins";
        } else if (type == FoodTypes.VEGETABLES_AND_FRUITS) {
            s = "Vegetables and Fruits";
        } else {
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

    public double getMass() {
        return mass;
    }

    public void setType(FoodTypes type) {
        this.type = type;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getTimeOfDay() {
        return this.timeOfDay;
    }

    public void setTimeOfDay(String s) {
        this.timeOfDay = s;
    }

    @Override
    //EFFECTS: returns food as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("calories", calories);
        json.put("mass", mass);
        json.put("timeOfDay", timeOfDay);

        return json;
    }
}