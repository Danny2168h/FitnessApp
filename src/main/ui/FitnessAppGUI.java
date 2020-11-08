package ui;

import model.Calculator;
import model.Exercise;
import model.Food;
import model.Person;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
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
    private JPanel mainMenu;


    public FitnessAppGUI() {
        startMenu = new StartMenu(this);
        initGUI();
        frame.add(startMenu);
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
        //goal menu
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
                //mainMenu(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initGUI() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setTitle("Fitness App");
        frame.setVisible(true);
    }

    public void setAccountNum(int num) {
        accountNum = num;
    }

    //MODIFIES: this
    //EFFECTS: loads the selected person into fitness app
    private void loadState() {
        jsonReader = new JsonReader("./data/person" + accountNum + ".json");
        try {
            person = jsonReader.read();
            System.out.println("Data has been successfully loaded!");
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
    }
}