package ui.menus;

import model.Calculator;
import model.Person;
import ui.FitnessAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the main menu of the fitness app program
public class MainMenu extends JPanel {

    private static final int BUTTON_WIDTH = 175;
    private static final int BUTTON_HEIGHT = BUTTON_WIDTH - 50;
    private static final int FONT_SIZE = 20;

    private final FitnessAppGUI fitnessAppGUI;
    private JButton addFood;
    private JButton viewFood;
    private JButton addExercise;
    private JButton viewExercise;
    private JButton saveState;
    private JPanel calorieCounter;

    //EFFECTS: Creates a new fitness app program
    public MainMenu(FitnessAppGUI fitnessAppGUI, String recentEvent) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setLayout(null);
        this.setSize(FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.setLocation(0, 0);
        addAccountName(fitnessAppGUI);
        addTitle();
        addRecentEvent(recentEvent);
        addFoodButton();
        addViewFoodButton();
        addExerciseButton();
        addViewExerciseButton();
        addSaveStateButton();
        addCalorieCounter();
        addBackground();
    }

    //MODIFIES: this
    //EFFECTS: adds account name label to panel
    private void addAccountName(FitnessAppGUI fitnessAppGUI) {
        JLabel name = new JLabel("Account Holder: " + fitnessAppGUI.getPerson().getName());
        name.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 4));
        name.setBounds(75,10, 800, 20);
        this.add(name);
    }

    //MODIFIES: this
    //EFFECTS: adds main title label to panel
    private void addTitle() {
        JLabel title = new JLabel("FITNESS APP: MAIN MENU");
        title.setFont(new Font("Calibri", Font.ITALIC, FONT_SIZE + 13));
        title.setBounds(75,40, 800, 50);
        this.add(title);
    }

    //MODIFIES: this
    //EFFECTS: adds background image to the panel
    private void addBackground() {
        ImageIcon background = new ImageIcon("./data/windows1080.jpg");
        JLabel backgroundImage = new JLabel(background);
        backgroundImage.setBounds(0,0,FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.add(backgroundImage);
    }

    //MODIFIES: this
    //EFFECTS: adds a JLabel to the panel that represents the most recent action
    private void addRecentEvent(String event) {
        if (!event.equals("")) {
            JLabel recentEvent = new JLabel("Recent Event: " + event);
            recentEvent.setFont(new Font("Calibri", Font.ITALIC, FONT_SIZE + 5));
            recentEvent.setForeground(Color.white);
            recentEvent.setBounds(215, 580, 800, 30);
            this.add(recentEvent);
        }
    }

    //MODIFIES: this
    //EFFECTS: adds the add food button to the panel
    private void addFoodButton() {
        addFood = new JButton("ADD FOODS");
        addFood.addActionListener(new AddFoodClickHandler());
        addFood.setBounds(212, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        addFood.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        this.add(addFood);
    }

    //MODIFIES: this
    //EFFECTS: adds the view foods button to the panel
    private void addViewFoodButton() {
        viewFood = new JButton("VIEW FOODS");
        viewFood.addActionListener(new ViewFoodClickHandler());
        viewFood.setBounds(412, 200, BUTTON_WIDTH, BUTTON_HEIGHT);
        viewFood.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        this.add(viewFood);
    }

    //MODIFIES: this
    //EFFECTS: adds the add exercise button to the panel
    private void addExerciseButton() {
        addExercise = new JButton("ADD EXERCISE");
        addExercise.addActionListener(new ExerciseClickHandler());
        addExercise.setBounds(212, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        addExercise.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        this.add(addExercise);
    }

    //MODIFIES: this
    //EFFECTS: adds the view exercise button to the panel
    private void addViewExerciseButton() {
        viewExercise = new JButton("VIEW EXERCISE");
        viewExercise.addActionListener(new ViewExerciseClickHandler());
        viewExercise.setBounds(412, 350, BUTTON_WIDTH, BUTTON_HEIGHT);
        viewExercise.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        this.add(viewExercise);
    }

    //MODIFIES: this
    //EFFECTS: adds the save state button to the panel
    private void addSaveStateButton() {
        saveState = new JButton("SAVE STATE");
        saveState.addActionListener(new SaveStateClickHandler());
        saveState.setBounds(312, 500, BUTTON_WIDTH, BUTTON_HEIGHT - 75);
        saveState.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
        this.add(saveState);
    }

    //MODIFIES: this
    //EFFECTS: adds the calories counter to the panel
    private void addCalorieCounter() {
        calorieCounter = new JPanel();
        calorieCounter.setLayout(null);
        calorieCounter.setBounds(75, 90, 650, 80);
        calorieCounter.setBackground(Color.CYAN);
        addLabels();
        addOperations();
        addMath();
        this.add(calorieCounter);
    }

    //MODIFIES: this
    //EFFECTS: computes the calories and adds them to the calorie counter
    private void addMath() {
        Calculator calc = new Calculator();
        Person p = fitnessAppGUI.getPerson();
        p.setDailyRecCalories(calc.calcDailyCal(p));
        JLabel goalCal = new JLabel(String.valueOf((int) p.getDailyRecCalories()));
        JLabel foodCal =  new JLabel(String.valueOf(calc.foodCalCalc(p)));
        JLabel exerciseCal = new JLabel(String.valueOf(calc.exerciseCalCalc(p)));
        JLabel remainingCal = new JLabel(String.valueOf((int) p.getDailyRecCalories() - calc.foodCalCalc(p)
                + calc.exerciseCalCalc(p)));
        goalCal.setBounds(80, 45, 125, 20);
        foodCal.setBounds(225, 45, 125, 20);
        exerciseCal.setBounds(390, 45, 125, 20);
        remainingCal.setBounds(555, 45, 125, 20);
        goalCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        foodCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        exerciseCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        remainingCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        calorieCounter.add(goalCal);
        calorieCounter.add(foodCal);
        calorieCounter.add(exerciseCal);
        calorieCounter.add(remainingCal);
    }

    //MODIFIES: this
    //EFFECTS: adds the mathematical operators to the calorie counter
    private void addOperations() {
        JLabel minus = new JLabel("-");
        JLabel plus = new JLabel("+");
        JLabel equals = new JLabel("=");
        minus.setBounds(160, 42, 25, 25);
        plus.setBounds(320, 42, 25, 25);
        equals.setBounds(490, 42, 25, 25);
        minus.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        plus.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        equals.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        calorieCounter.add(minus);
        calorieCounter.add(plus);
        calorieCounter.add(equals);
    }

    //MODIFIES: this
    //EFFECTS: adds the labels to the calories counter
    private void addLabels() {
        JLabel goalCal = new JLabel("GOAL:");
        JLabel foodCal = new JLabel("FOODS:");
        JLabel exerciseCal = new JLabel("EXERCISE:");
        JLabel remainingCal = new JLabel("REMAINING:");
        goalCal.setBounds(75, 15, 125, 20);
        foodCal.setBounds(215, 15, 125, 20);
        exerciseCal.setBounds(375, 15, 125, 20);
        remainingCal.setBounds(525, 15, 125, 20);
        goalCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        foodCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        exerciseCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        remainingCal.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE - 3));
        calorieCounter.add(goalCal);
        calorieCounter.add(foodCal);
        calorieCounter.add(exerciseCal);
        calorieCounter.add(remainingCal);
    }

    //idea for action handler sourced from complete shape player project provided by CPSC 210
    private class AddFoodClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: takes user to add food menu
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.addFoodMenu();
        }

    }

    private class ViewFoodClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: takes user to view foods menu
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.viewFoodMenu();
        }

    }

    private class ExerciseClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: takes user to add exercise menu
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.addExercise();
        }

    }

    private class ViewExerciseClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: takes user to view all exercises they have stores
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.viewExercises();
        }

    }

    private class SaveStateClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: prompts user to save state
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.confirmSave();
        }
    }
}
