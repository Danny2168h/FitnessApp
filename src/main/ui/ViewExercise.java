package ui;

import model.Exercise;
import model.Food;
import model.FoodTypes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewExercise extends JDialog {

    private GridBagConstraints gc;
    private List<Exercise> exercises;

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

    private void setConstraints() {
        gc = new GridBagConstraints();
        gc.weightx = 1.5;
        gc.weighty = 1;
        titleSetUp();
        loopExercises();
    }

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

