package model;


import java.util.ArrayList;
import java.util.List;

//has personal information about the person, their food eaten, activities, their goals are stored here
//foods and activities can only be edited the day of. we should be able to access profile and update medical
//conditions only.

//units are in CM and KG for height and weight respectively
public class Person {

    //Persons descriptors
    private String name;
    private double age;
    private double startWeight;
    private double height;
    //True represents male, false represents female
    private Boolean sex;

    public int caloriesToday;

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
}
