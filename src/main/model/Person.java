package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;
import java.util.List;


//Represents a person with their information along with the foods and exercises that they have done in the day
//units are in CM and KG for height and weight respectively
public class Person implements Writeable {

    //Persons descriptors
    private String name;
    private double age;
    private double startWeight;
    private double height;
    //True represents male, false represents female
    private Boolean sex;

    //Goal weight and Daily Calories
    private double goalWeight;
    private double goalDays;
    private double dailyRecCalories;

    //Foods and exercises
    private List<Food> breakfast;
    private List<Food> lunch;
    private List<Food> dinner;
    private List<Food> snacks;
    private List<Exercise> exercises;

    //EFFECTS: Creates a new person instance with fields set to 0 and empty lists.
    public Person() {

        breakfast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();
        snacks = new ArrayList<>();
        exercises = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds food item to breakfast
    public void addBreakfast(Food f) {
        breakfast.add(f);
    }

    //MODIFIES: this
    //EFFECTS: adds food item to lunch
    public void addLunch(Food f) {
        lunch.add(f);
    }

    //MODIFIES: this
    //EFFECTS: adds food item to dinner
    public void addDinner(Food f) {
        dinner.add(f);
    }

    //MODIFIES: this
    //EFFECTS: adds food item to snacks
    public void addSnacks(Food f) {
        snacks.add(f);
    }

    //MODIFIES: this
    //EFFECTS: adds exercise to exercises
    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    //REQUIRES: String to be of "m" or "f"
    //MODIFIES: this
    //EFFECTS: based on input set
    public String setProperSex(String cmd) {
        if (cmd.equals("m")) {
            this.setMale();
            return "You have set your gender to: Male";
        } else {
            this.setFemale();
            return "You have set your gender to: Female";
        }
    }

    //Setters
    public void setName(String str) {
        this.name = str;
    }

    public void setHeight(double h) {
        this.height = h;
    }

    public void setStartWeight(double w) {
        this.startWeight = w;
    }

    public void setGoalWeight(double w) {
        this.goalWeight = w;
    }

    public void setGoalDays(double g) {
        this.goalDays = g;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setFemale() {
        this.sex = false;
    }

    public void setMale() {
        this.sex = true;
    }

    public void setDailyRecCalories(double c) {
        this.dailyRecCalories = c;
    }
    
    public void setBreakfast(List<Food> breakfast) {
        this.breakfast = breakfast;
    }

    public void setLunch(List<Food> lunch) {
        this.lunch = lunch;
    }

    public void setDinner(List<Food> dinner) {
        this.dinner = dinner;
    }

    public void setSnacks(List<Food> snacks) {
        this.snacks = snacks;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    //Getters
    public String getName() {
        return name;
    }

    public double getStartWeight() {
        return startWeight;
    }

    public Boolean getSex() {
        return sex;
    }

    public double getGoalWeight() {
        return goalWeight;
    }

    public double getGoalDays() {
        return goalDays;
    }

    public double getDailyRecCalories() {
        return dailyRecCalories;
    }

    public List<Food> getBreakfast() {
        return breakfast;
    }

    public List<Food> getLunch() {
        return lunch;
    }

    public List<Food> getDinner() {
        return dinner;
    }

    public List<Food> getSnacks() {
        return snacks;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public double getHeight() {
        return this.height;
    }

    public double getAge() {
        return this.age;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("startWeight", startWeight);
        json.put("height", height);
        json.put("sex", sex);
        json.put("goalWeight", goalWeight);
        json.put("goalDays", goalDays);
        json.put("dailyRecCalories", dailyRecCalories);
        json.put("breakfast", breakfastToJson());
        json.put("lunch", lunchToJson());
        json.put("dinner", dinnerToJson());
        json.put("snacks", snacksToJson());
        json.put("exercises", exerciseToJson());

        return json;
    }

    //EFFECTS: returns breakfast in this as a JSON array
    private JSONArray breakfastToJson() {
        JSONArray array = new JSONArray();
        for (Food f : breakfast) {
            array.put(f.foodToJson());
        }
        return array;
    }

    //EFFECTS: returns lunch in this as a JSON array
    private JSONArray lunchToJson() {
        JSONArray array = new JSONArray();
        for (Food f : lunch) {
            array.put(f.foodToJson());
        }
        return array;
    }

    //EFFECTS: returns dinner in this as a JSON array
    private JSONArray dinnerToJson() {
        JSONArray array = new JSONArray();
        for (Food f : dinner) {
            array.put(f.foodToJson());
        }
        return array;
    }

    //EFFECTS: returns snacks in this as a JSON array
    private JSONArray snacksToJson() {
        JSONArray array = new JSONArray();
        for (Food f : snacks) {
            array.put(f.foodToJson());
        }
        return array;
    }

    //EFFECTS: returns exercise in this as a JSON array
    private JSONArray exerciseToJson() {
        JSONArray array = new JSONArray();
        for (Exercise e : exercises) {
            array.put(e.exerciseToJson());
        }
        return array;
    }

}
