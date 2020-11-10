package ui.menus;

import model.Calculator;
import model.Person;
import ui.FitnessAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//represents menu that allows user to set their goals
public class SetGoalMenu extends JPanel implements ActionListener {

    private static final int LABEL_X = 220;
    private static final int GOALWEIGHT_Y = 125;
    private static final int TIME_Y = GOALWEIGHT_Y + 75;

    private FitnessAppGUI fitnessAppGUI;
    private JTextField goalWeight;
    private JTextField time;
    private JLabel negativeInputs;
    private JLabel invalidInput;
    private JLabel dietUnSafe;
    private JButton submit;

    //EFFECTS: creates new set goal menu
    public SetGoalMenu(FitnessAppGUI fitnessAppGUI) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setLayout(null);
        this.setSize(FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setBackground(Color.LIGHT_GRAY);
        JLabel title = new JLabel("Please Enter Your Desired Goals:");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setBounds(180, 10, 600, 100);
        this.add(title);
        addLabels();
        addInputFields();
        submit = new JButton("Submit");
        submit.setBounds(455, TIME_Y + 75, 90, 25);
        submit.addActionListener(this);
        this.add(submit);
    }

    //MODIFIES: this
    //EFFECTS: adds the input fields to the panel
    private void addInputFields() {
        goalWeight = new JTextField();
        goalWeight.setBounds(LABEL_X + 205, GOALWEIGHT_Y - 5, 140, 25);

        time = new JTextField();
        time.setBounds(LABEL_X + 205, TIME_Y - 5, 140, 25);

        this.add(goalWeight);
        this.add(time);
    }

    //MODIFIES: this
    //EFFECTS: adds the labels to the panel
    private void addLabels() {
        makeLabels("Goal Weight (KG): ", LABEL_X, GOALWEIGHT_Y);
        makeLabels("Days To Achieve Goal: ", LABEL_X, TIME_Y);
    }

    //REQUIRES: x and y > 0 and also in bounds of size of panel
    //MODIFIES: this
    //EFFECTS: adds the labels to the panel
    private void makeLabels(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setBounds(x, y, 200, 20);
        this.add(label);
    }

    // MODIFIES: this
    // EFFECTS: processes button press, displays error message if inputs invalid or negative,
    //          otherwise updates person field in FitnessAppGUI
    public void actionPerformed(ActionEvent e) {
        String strCalories = goalWeight.getText();
        String strTime = time.getText();
        removeAllOtherErrorLabels();
        try {
            int calories = Integer.parseInt(strCalories);
            int time = Integer.parseInt(strTime);
            if (checkValid(calories, time)) {
                if (checkSafety()) {
                    nextSteps();
                }
            }
        } catch (NumberFormatException ex) {
            if (!checkComponentContains(invalidInput)) {
                invalidInput = new JLabel("Inputs for Weight and Days must be whole numbers");
                invalidInput.setBounds(250, TIME_Y + 40, 400, 20);
                this.add(invalidInput);
            }
        }
        this.revalidate();
        this.repaint();
    }

    //REQUIRES: person must not have 0 for weight or goal weight
    //EFFECTS: checks safety of persons given diet plan
    private boolean checkSafety() {
        Calculator calc = new Calculator();
        Person p = fitnessAppGUI.getPerson();
        p.setGoalWeight(Integer.parseInt(goalWeight.getText()));
        p.setGoalDays(Integer.parseInt(time.getText()));
        if (!calc.checkDietSafety(fitnessAppGUI.getPerson())) {
            if (!checkComponentContains(dietUnSafe)) {
                dietUnSafe = new JLabel("Your diet plan is unsafe, please re-evaluate");
                dietUnSafe.setBounds(250, TIME_Y + 40, 400, 20);
                this.add(dietUnSafe);
            }
            return false;
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: checks if calories and time are negative or 0
    private boolean checkValid(int calories, int time) {
        if (time <= 0 || calories <= 0) {
            if (!checkComponentContains(negativeInputs)) {
                negativeInputs = new JLabel("Weight and days must be > 0");
                negativeInputs.setBounds(250, TIME_Y + 40, 400, 20);
                this.add(negativeInputs);
            }
            return false;
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: removes all error labels
    private void removeAllOtherErrorLabels() {
        removeErrorLabel(negativeInputs);
        removeErrorLabel(invalidInput);
        removeErrorLabel(dietUnSafe);
    }

    //MODIFIES: this
    //EFFECTS: passes user inputs into FitnessAppGUI
    private void nextSteps() {
        String strGoalWeight = goalWeight.getText();
        int goalWeight = Integer.parseInt(strGoalWeight);
        String strTime = time.getText();
        int timeNum = Integer.parseInt(strTime);

        fitnessAppGUI.afterSetGoal(goalWeight, timeNum);
    }

    //EFFECTS: Checks if a given label is currently being displayed on the panel
    private boolean checkComponentContains(JLabel label) {
        Component[] componentList = this.getComponents();

        for (Component c : componentList) {
            if (c.equals(label)) {
                return true;
            }
        }
        return false;
    }

    //MODIFIES: this
    //EFFECTS: removes the given error label from the JPanel
    private void removeErrorLabel(JLabel label) {
        Component[] componentList = this.getComponents();

        for (Component c : componentList) {
            if (c.equals(label)) {
                this.remove(c);
            }
        }
    }
}

