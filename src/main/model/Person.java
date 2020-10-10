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
    private int age;
    private int startWeight;
    private int height;
    //True represents male, false represents female
    private Boolean sex;
    private List<String> medicalConditions;

    //Goal weight and Daily Calories
    private int goalWeight;
    private int goalDays;
    private int dailyRecCalories;

    //Foods and exercises
    private List<Food> breakfast;
    private List<Food> lunch;
    private List<Food> dinner;
    private List<Food> snacks;
    private List<PhysicalActivity> activities;

    //EFFECTS: Creates a new person instance with fields set to 0 and empty lists.
    public Person(String name) {
        this.name = name;
        this.age = 0;
        this.startWeight = 0;
        this.height = 0;
        this.sex = true;
        medicalConditions = new ArrayList<>();

        this.goalWeight = 0;
        this.dailyRecCalories = 0;

        breakfast = new ArrayList<>();
        lunch = new ArrayList<>();
        dinner = new ArrayList<>();
        activities = new ArrayList<>();
    }


    //REQUIRES: non-empty string
    //MODIFIES: this
    //EFFECTS: sets name to string input
    public void setName(String str) {
        this.name = str;
    }

    //REQUIRES: weight > 0
    //MODIFIES: this
    //EFFECTS: sets weight to input
    public void setHeight(int h) {
        this.height = h;
    }

    //REQUIRES: weight > 0
    //MODIFIES: this
    //EFFECTS: sets weight to input
    public void setStartWeight(int w) {
        this.startWeight = w;
    }

    //REQUIRES: weight > 0
    //MODIFIES: this
    //EFFECTS: sets goal weight to input
    public void setGoalWeight(int w) {
        this.goalWeight = w;
    }

    //REQUIRES: Days > 0
    //MODIFIES: this
    //EFFECTS: sets goalDays to input
    public void setGoalDays(int g) {
        this.goalDays = g;
    }

    //REQUIRES: age > 0
    //MODIFIES: this
    //EFFECTS: sets age to input
    public void setAge(int age) {
        this.age = age;
    }

    //MODIFIES: this
    //EFFECTS: sets sex to female
    public void setFemale() {
        this.sex = false;
    }

    //MODIFIES: this
    //EFFECTS: sets sex to male
    public void setMale() {
        this.sex = true;
    }

    //REQUIRES: calories must be greater than 0
    //MODIFIES: this
    //EFFECTS: sets DailyRecCalories to input
    public void setDailyRecCalories(int c) {
        this.dailyRecCalories = c;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getStartWeight() {
        return startWeight;
    }

    public int getHeight() {
        return height;
    }

    public Boolean getSex() {
        return sex;
    }

    public List<String> getMedicalConditions() {
        return medicalConditions;
    }

    public int getGoalWeight() {
        return goalWeight;
    }

    public int getGoalDays() {
        return goalDays;
    }

    public int getDailyRecCalories() {
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

    public List<PhysicalActivity> getActivities() {
        return activities;
    }
}
