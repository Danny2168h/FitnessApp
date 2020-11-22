package ui.menus;

import model.FoodTypes;
import ui.FitnessAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the menu that allows people to add foods to the foods eaten
public class AddFoodMenu extends JPanel implements ActionListener {

    private static final int LABEL_X = 250;
    private static final int NAME_Y = 125;
    private static final int TYPE_Y = NAME_Y + 75;
    private static final int MASS_Y = TYPE_Y + 75;
    private static final int MEALTIME_Y = MASS_Y + 75;
    private static final int CALORIES_Y = MEALTIME_Y + 75;

    private FitnessAppGUI fitnessAppGUI;
    private JTextField name;
    private JComboBox type;
    private JTextField mass;
    private JComboBox mealTime;
    private JTextField calories;
    private JButton submit;
    private JLabel negativeInputs;
    private JLabel invalidInput;

    //EFFECTS: Creates a new add food menu
    public AddFoodMenu(FitnessAppGUI fitnessAppGUI) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setLayout(null);
        this.setSize(FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setBackground(Color.LIGHT_GRAY);

        JLabel title = new JLabel("Enter Details About Food Below:");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setBounds(180, 10, 600, 100);
        this.add(title);

        addLabels();
        addInputFields();

        submit = new JButton("Submit");
        submit.setBounds(455, CALORIES_Y + 75, 90, 25);
        submit.addActionListener(this);

        this.add(submit);
    }

    //MODIFIES: this
    //EFFECTS: adds input fields into JPanel
    private void addInputFields() {
        name = new JTextField();
        name.setBounds(LABEL_X + 155, NAME_Y - 5, 140, 25);

        String[] choices = {"Vegetables and Fruits", "Proteins", "Grains", "Others"};
        type = new JComboBox(choices);
        type.setLayout(null);
        type.setBounds(LABEL_X + 155, TYPE_Y - 5, 140, 25);

        mass = new JTextField();
        mass.setBounds(LABEL_X + 155, MASS_Y - 5, 140, 25);

        String[] mealtimes = {"Breakfast", "Lunch", "Dinner", "Snacks"};
        mealTime = new JComboBox(mealtimes);
        mealTime.setBounds(LABEL_X + 155, MEALTIME_Y - 5, 140, 25);

        calories = new JTextField();
        calories.setBounds(LABEL_X + 155, CALORIES_Y - 5, 140, 25);

        this.add(name);
        this.add(type);
        this.add(mass);
        this.add(mealTime);
        this.add(calories);
    }

    //MODIFIES: this
    //EFFECTS: adds labels into JPanel
    private void addLabels() {
        makeLabels("Food Name: ", LABEL_X, NAME_Y);
        makeLabels("Food Type: ", LABEL_X, TYPE_Y);
        makeLabels("Mass (g): ", LABEL_X, MASS_Y);
        makeLabels("Meal Time: ", LABEL_X, MEALTIME_Y);
        makeLabels("Calories: ", LABEL_X, CALORIES_Y);
    }

    //REQUIRES: x and y to both be non negative integers and not greater than size of JPanel
    //MODIFIES: this
    //EFFECTS: creates labels based on parameters
    private void makeLabels(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setBounds(x, y, 150, 20);
        this.add(label);
    }

    @Override
    // MODIFIES: this
    // EFFECTS: processes button press, displays error message if inputs invalid or negative,
    //          otherwise updates person field in FitnessAppGUI
    public void actionPerformed(ActionEvent e) {
        String foodMass = mass.getText();
        String foodCal = calories.getText();
        removeAllOtherErrorLabels();
        try {
            int mass = Integer.parseInt(foodMass);
            int calories = Integer.parseInt(foodCal);
            if (checkValid(mass, calories)) {
                nextSteps();
            }
        } catch (NumberFormatException ex) {
            if (!checkComponentContains(invalidInput)) {
                invalidInput = new JLabel("Invalid Inputs for Mass or Calories");
                invalidInput.setBounds(310, CALORIES_Y + 40, 250, 20);
                this.add(invalidInput);
            }
        }
        this.revalidate();
        this.repaint();
    }

    //EFFECTS: produces true if calories and mass are both greater than 0 false otherwise
    private boolean checkValid(int mass, int calories) {
        if (mass <= 0 || calories <= 0) {
            if (!checkComponentContains(negativeInputs)) {
                negativeInputs = new JLabel("Mass and Calories must be > 0");
                negativeInputs.setBounds(310, CALORIES_Y + 40, 250, 20);
                this.add(negativeInputs);
            }
            return false;
        }
        return true;
    }

    //MODIFIES: this
    //EFFECTS: removes all other error labels from JFrame
    private void removeAllOtherErrorLabels() {
        removeErrorLabel(negativeInputs);
        removeErrorLabel(invalidInput);
    }

    //MODIFIES: this
    //EFFECTS: passes user inputs into the FitnessAppGUI
    private void nextSteps() {
        String strName = name.getText();
        String strMass = mass.getText();
        int mass = Integer.parseInt(strMass);
        int mealTimeSelectedIndex = mealTime.getSelectedIndex();
        String strCalories = calories.getText();
        int calories = Integer.parseInt(strCalories);
        int foodTypeSelectedIndex = type.getSelectedIndex();

        fitnessAppGUI.afterAddFood(strName, resolveType(foodTypeSelectedIndex), mass, mealTimeSelectedIndex, calories);
    }

    //EFFECTS: converts FoodType into a string that represents the food type
    private FoodTypes resolveType(int foodTypeSelectedIndex) {
        if (foodTypeSelectedIndex == 0) {
            return FoodTypes.VEGETABLES_AND_FRUITS;
        } else if (foodTypeSelectedIndex == 1) {
            return FoodTypes.PROTEINS;
        } else if (foodTypeSelectedIndex == 2) {
            return FoodTypes.GRAINS;
        } else if (foodTypeSelectedIndex == 3) {
            return FoodTypes.OTHERS;
        }
        return null;
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

