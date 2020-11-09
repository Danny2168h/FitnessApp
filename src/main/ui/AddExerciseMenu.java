package ui;

import model.FoodTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddExerciseMenu extends JPanel implements ActionListener {

    private static final int LABEL_X = 250;
    private static final int NAME_Y = 125;
    private static final int CALORIES_Y = NAME_Y + 75;
    private static final int TIME_Y = CALORIES_Y + 75;

    private FitnessAppGUI fitnessAppGUI;
    private JTextField name;
    private JTextField time;
    private JTextField calories;
    private JButton submit;
    private JLabel negativeInputs;
    private JLabel invalidInput;

    public AddExerciseMenu(FitnessAppGUI fitnessAppGUI) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setLayout(null);
        this.setSize(FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setBackground(Color.LIGHT_GRAY);
        JLabel title = new JLabel("Enter Details About Exercise Below:");
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

    private void addInputFields() {
        name = new JTextField();
        name.setBounds(LABEL_X + 155, NAME_Y - 5, 140, 25);

        calories = new JTextField();
        calories.setBounds(LABEL_X + 155, CALORIES_Y - 5, 140, 25);

        time = new JTextField();
        time.setBounds(LABEL_X + 155, TIME_Y - 5, 140, 25);

        this.add(name);
        this.add(calories);
        this.add(time);
    }

    private void addLabels() {
        makeLabels("Exercise Name: ", LABEL_X, NAME_Y);
        makeLabels("Calories: ", LABEL_X, CALORIES_Y);
        makeLabels("Time(mins): ", LABEL_X, TIME_Y);
    }

    private void makeLabels(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setBounds(x, y, 150, 20);
        this.add(label);
    }

    // MODIFIES: FitnessAppGUI
    // EFFECTS: displays error message if inputs invalid, otherwise updates person field in FitnessAppGUI
    public void actionPerformed(ActionEvent e) {
        String strCalories = calories.getText();
        String strTime = time.getText();
        removeAllOtherErrorLabels();
        try {
            int calories = Integer.parseInt(strCalories);
            int time = Integer.parseInt(strTime);
            if (checkValid(calories, time)) {
                nextSteps();
            }
        } catch (NumberFormatException ex) {
            if (!checkComponentContains(invalidInput)) {
                invalidInput = new JLabel("Inputs for Calories and mass must be whole numbers");
                invalidInput.setBounds(250, TIME_Y + 40, 400, 20);
                this.add(invalidInput);
            }
        }
        this.revalidate();
        this.repaint();
    }

    private boolean checkValid(int calories, int time) {
        if (time <= 0 || calories <= 0) {
            if (!checkComponentContains(negativeInputs)) {
                negativeInputs = new JLabel("Calories and Mass must be > 0");
                negativeInputs.setBounds(250, TIME_Y + 40, 400, 20);
                this.add(negativeInputs);
            }
            return false;
        }
        return true;
    }


    private void removeAllOtherErrorLabels() {
        removeErrorLabel(negativeInputs);
        removeErrorLabel(invalidInput);
    }

    private void nextSteps() {
        String strName = name.getText();
        String strCalories = calories.getText();
        int calNum = Integer.parseInt(strCalories);
        String strTime = time.getText();
        int timeNum = Integer.parseInt(strTime);

        fitnessAppGUI.afterAddExercise(strName, calNum, timeNum);
    }


    private boolean checkComponentContains(JLabel label) {
        Component[] componentList = this.getComponents();

        for (Component c : componentList) {
            if (c.equals(label)) {
                return true;
            }
        }
        return false;
    }

    private void removeErrorLabel(JLabel label) {
        Component[] componentList = this.getComponents();

        for (Component c : componentList) {
            if (c.equals(label)) {
                this.remove(c);
            }
        }
    }
}
