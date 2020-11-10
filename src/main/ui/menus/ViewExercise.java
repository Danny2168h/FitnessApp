package ui.menus;

import model.Exercise;
import model.Food;
import model.FoodTypes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Represents the popup window that displays all logged exercises by user
public class ViewExercise extends JDialog {

    private GridBagConstraints gc;
    private List<Exercise> exercises;

    //EFFECTS: creates new popup window that displays all logged exercises
    public ViewExercise(List<Exercise> exercises) {
        this.exercises = exercises;
        this.setBounds(0, 0, 400, 300);
        this.setTitle("Your exercises");
        this.setResizable(true);
        this.setVisible(true);
        this.setBackground(Color.white);

        this.setLayout(new GridBagLayout());

        setConstraints();
    }

    //MODIFIES: this
    //EFFECTS: sets grid bag constraints and adds exercises in correct positions on dialog window
    private void setConstraints() {
        gc = new GridBagConstraints();
        gc.weightx = 1.5;
        gc.weighty = 1;
        titleSetUp();
        loopExercises();
    }

    //MODIFIES: this
    //EFFECTS: adds each exercise to the dialog window
    private void loopExercises() {
        int y = 1;
        for (Exercise e : exercises) {
            JLabel name = new JLabel(e.getName());
            gc.gridx = 0;
            gc.gridy = y;
            add(name, gc);

            JLabel calories = new JLabel(String.valueOf(e.getCalories()));
            gc.gridx = 1;
            gc.gridy = y;
            add(calories, gc);

            JLabel type = new JLabel(String.valueOf(e.getTime()));
            gc.gridx = 2;
            gc.gridy = y;
            add(type, gc);

            y += 1;
        }
    }

    //MODIFIES: this
    //EFFECTS: sets up the titles for the table
    private void titleSetUp() {
        JLabel name = new JLabel("Name: ");
        gc.gridx = 0;
        gc.gridy = 0;
        add(name, gc);

        JLabel calories = new JLabel("Calories: ");
        gc.gridx = 1;
        gc.gridy = 0;
        add(calories, gc);

        JLabel type = new JLabel("Time: ");
        gc.gridx = 2;
        gc.gridy = 0;
        add(type, gc);
    }
}

