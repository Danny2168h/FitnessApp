package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.menus.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Fitness App class contains the code for the graphical user interface
public class FitnessAppGUI {

    public static final int FRAME_HEIGHT = 800;
    public static final int FRAME_WIDTH = 800;

    private Person person;

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
    private JPanel viewFoodMenu;


    //EFFECTS: Constructs new FitnessAppGUI
    public FitnessAppGUI() {
        startMenu = new StartMenu(this);
        initGUI();
        frame.add(startMenu);
    }

    //MODIFIES: this
    //EFFECTS: initializes the JFrame with proper dimensions
    private void initGUI() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Fitness App");
        frame.setVisible(true);
    }

    //REQUIRES: weight > 0
    //MODIFIES: this
    //EFFECTS: sets fields in person, and advances user to the set goal menu
    public void afterCreateAccount(int weight, boolean sex, String strName) {
        person = new Person();
        person.setName(strName);
        person.setStartWeight(weight);
        if (sex) {
            person.setMale();
        } else {
            person.setFemale();
        }
        frame.remove(createAccountMenu);
        frame.repaint();
        setGoalMenu = new SetGoalMenu(this);
        frame.add(setGoalMenu);
    }

    //REQUIRES: weight and days to be > 0
    //MODIFIES: this
    //EFFECTS: sets fields in person, and returns user to main menu
    public void afterSetGoal(int goalWeight, int days) {
        frame.remove(setGoalMenu);
        frame.repaint();
        person.setGoalWeight(goalWeight);
        person.setGoalDays(days);
        mainMenu = new MainMenu(this, "Just created new account");
        frame.add(mainMenu);
    }

    //REQUIRES: int from 1-3 inclusive
    //MODIFIES: this
    //EFFECTS: processes commands from start menu and selects to either direct user to main menu or account creation
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

    //MODIFIES: this
    //EFFECTS: adds the add food menu to the main frame
    public void addFoodMenu() {
        frame.remove(mainMenu);
        frame.repaint();
        addFoodMenu = new AddFoodMenu(this);
        frame.add(addFoodMenu);
    }

    //MODIFIES: this
    //EFFECTS: adds the add exercise menu to the main frame
    public void addExercise() {
        frame.remove(mainMenu);
        frame.repaint();
        addExerciseMenu = new AddExerciseMenu(this);
        frame.add(addExerciseMenu);
    }

    //REQUIRES: mass and calories > 0 and selected index between 0 and 3 inclusive
    //MODIFIES: this
    //EFFECTS: updates the person field and returns user to main menu
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

    //REQUIRES: calories and time > 0
    //MODIFIES: this
    //EFFECTS: updates the person field and returns user to main menu
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

    //EFFECTS: creates a popup window that asks user to confirm the save of data
    public void confirmSave() {
        new ConfirmSave(this);
    }

    //MODIFIES: this
    //EFFECTS: Saves data into Json file and returns user to main menu
    public void afterSaveState() {
        saveState();
        frame.remove(mainMenu);
        frame.repaint();
        mainMenu = new MainMenu(this, "Successfully saved data");
        frame.add(mainMenu);
    }

    //MODIFIES: this
    //EFFECTS: adds view foods menu to main frame
    public void viewFoodMenu() {
        frame.remove(mainMenu);
        frame.repaint();
        viewFoodMenu = new ViewFoodMenu(this);
        frame.add(viewFoodMenu);
    }

    //REQUIRES: i be in the range from 0-3 inclusive
    //EFFECTS: Creates a pop up that displays the foods eaten based on the meal time the user requested
    public void displayFoods(int i) {
        if (i == 0) {
            new DisplayFoodPopUp(this, person.getBreakfast(), "breakfast");
        } else if (i == 1) {
            new DisplayFoodPopUp(this, person.getLunch(), "lunch");
        } else if (i == 2) {
            new DisplayFoodPopUp(this, person.getDinner(), "dinner");
        } else if (i == 3) {
            new DisplayFoodPopUp(this, person.getSnacks(), "snacks");
        }
    }

    //MODIFIES: this
    //EFFECTS: returns user to main menu
    public void returnToMainMenuFromViewFoods() {
        frame.remove(viewFoodMenu);
        frame.repaint();
        mainMenu = new MainMenu(this, "Returned from viewing foods");
        frame.add(mainMenu);
    }

    //EFFECTS: creates pop up menu that displays all exercises logged
    public void viewExercises() {
        new ViewExercise(person.getExercises());
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

    //getter
    public Person getPerson() {
        return person;
    }

}
