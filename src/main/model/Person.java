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

    public int caloriesToday;

    //Goal weight and Daily Calories
    private int goalWeight;
    private int goalDays;
    private int dailyRecCalories;

    //Foods and exercises
    private List<Food> breakfast;
    private List<Food> lunch;
    private List<Food> dinner;
    private List<Food> snacks;
    private List<Exercise> exercises;

    //EFFECTS: Creates a new person instance with fields set to 0 and empty lists.
    public Person() {
        this.name = "";
        this.age = 0;
        this.startWeight = 0;
        this.height = 0;
        this.sex = true;

        this.caloriesToday = 0;

        this.goalWeight = 0;
        this.dailyRecCalories = 0;

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


    //Setters
    public void setName(String str) {
        this.name = str;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public void setStartWeight(int w) {
        this.startWeight = w;
    }

    public void setGoalWeight(int w) {
        this.goalWeight = w;
    }

    public void setGoalDays(int g) {
        this.goalDays = g;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFemale() {
        this.sex = false;
    }

    public void setMale() {
        this.sex = true;
    }

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

    public List<Exercise> getExercises() {
        return exercises;
    }
}
