package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FitnessAppGUI {

    public static final int FRAME_HEIGHT = 800;
    public static final int FRAME_WIDTH = 800;

    private static final int PANEL_WIDTH = 800;

    private Person person;
    private Scanner input;
    private Calculator calc;
    private Food food;
    private Exercise exercise;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //Takes value from 1-3 and represents which account is being modified
    public int accountNum;

    private JFrame frame;
    private JPanel startMenu;
    private JPanel createAccountMenu;
    private JPanel setGoalMenu;
    private JPanel mainMenu;
    private JPanel addFoodMenu;
    private JPanel addExerciseMenu;


    public FitnessAppGUI() {
        startMenu = new StartMenu(this);
        initGUI();
        frame.add(startMenu);
    }

    private void initGUI() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Fitness App");
        frame.setVisible(true);
    }

    //REQUIRES: int from 1-3 inclusive
    //MODIFIES: this
    //EFFECTS: processes commands from start menu and advances program
    public void afterAccountSelect(int accountNum) {
        this.accountNum = accountNum;
        jsonReader = new JsonReader("./data/person" + accountNum + ".json");
        frame.remove(startMenu);
        frame.repaint();
        try {
            if (jsonReader.isEmptyFile()) {
                createAccountMenu = new CreateAccountMenu(this);
                frame.add(createAccountMenu);
            } else {
                loadState();
                mainMenu = new MainMenu(this, "Just logged back on!");
                frame.add(mainMenu);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void afterCreateAccount(int weight, boolean sex, String strName) {
        person = new Person();
        person.setName(strName);
        person.setStartWeight(weight);
        if (sex) {
            person.setMale();
        }
        frame.remove(createAccountMenu);
        frame.repaint();
        setGoalMenu = new SetGoalMenu(this);
        frame.add(setGoalMenu);
    }

    public void afterSetGoal(int goalWeight, int days) {
        frame.remove(setGoalMenu);
        frame.repaint();
        person.setGoalWeight(goalWeight);
        person.setGoalDays(days);
        mainMenu = new MainMenu(this, "Just created new account");
        frame.add(mainMenu);
    }

    //MODIFIES: this
    //EFFECTS: loads the selected person into fitness app
    private void loadState() {
        jsonReader = new JsonReader("./data/person" + accountNum + ".json");
        try {
            person = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }

    public Person getPerson() {
        return person;
    }

    public void addFoodMenu() {
        frame.remove(mainMenu);
        frame.repaint();
        addFoodMenu = new AddFoodMenu(this);
        frame.add(addFoodMenu);
    }

    public void addExercise() {
        frame.remove(mainMenu);
        frame.repaint();
        addExerciseMenu = new AddExerciseMenu(this);
        frame.add(addExerciseMenu);
    }

    public void afterAddFood(String strName, FoodTypes type, int mass, int mealTimeSelectedIndex, int calories) {
        frame.remove(addFoodMenu);
        frame.repaint();
        Food f = null;
        if (mealTimeSelectedIndex == 0) {
            f = new Food(strName, type, calories, mass, "Added " + strName + " to breakfast");
            person.addBreakfast(f);
        } else if (mealTimeSelectedIndex == 1) {
            f = new Food(strName, type, calories, mass, "Added " + strName + " to lunch");
            person.addLunch(f);
        } else if (mealTimeSelectedIndex == 2) {
            f = new Food(strName, type, calories, mass, "Added " + strName + " to dinner");
            person.addDinner(f);
        } else if (mealTimeSelectedIndex == 3) {
            f = new Food(strName, type, calories, mass, "Added " + strName + " to snacks");
            person.addSnacks(f);
        }
        mainMenu = new MainMenu(this, f.getTimeOfDay());
        frame.add(mainMenu);
    }

    public void afterAddExercise(String strName, int calNum, int timeNum) {
        frame.remove(addExerciseMenu);
        frame.repaint();
        Exercise e = new Exercise();
        e.setName(strName);
        e.setCalories(calNum);
        e.setTime(timeNum);
        person.addExercise(e);
        mainMenu = new MainMenu(this, "Added " + e.getName() + " to exercises");
        frame.add(mainMenu);
    }

    public void confirmSave() {
        new ConfirmSave(this);
    }

    public void afterSaveState() {
        saveState();
        frame.remove(mainMenu);
        frame.repaint();
        mainMenu = new MainMenu(this, "Successfully saved data");
        frame.add(mainMenu);
    }

    //EFFECTS: Stores the current state of person into json file
    private void saveState() {
        jsonWriter = new JsonWriter("./data/person" + accountNum + ".json");
        try {
            jsonWriter.open();
            jsonWriter.write(person);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("unable to write to file");
        }
    }
}
