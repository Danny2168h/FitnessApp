package ui;

import model.Food;
import model.FoodTypes;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//Grid bag constraint code learned and sourced partially 
//from https://youtu.be/YKaea4ezQQE

public class DisplayFoodPopUp extends JDialog {

    private FitnessAppGUI fitnessAppGUI;
    private static List<Food> foods;
    private GridBagConstraints gc;
    
    public DisplayFoodPopUp(FitnessAppGUI fitnessAppGUI, List<Food> foods, String time) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.foods = foods;
        this.setBounds(0, 0, 400, 300);
        this.setTitle("Your " + time + " foods");
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
        loopFoods();
    }

    private void loopFoods() {
        int y = 1;
        for (Food f : foods) {
            JLabel name = new JLabel(f.getName());
            gc.gridx = 0;
            gc.gridy = y;
            add(name, gc);

            JLabel calories = new JLabel(String.valueOf(f.getCalories()));
            gc.gridx = 1;
            gc.gridy = y;
            add(calories, gc);

            JLabel type = new JLabel(typeToString(f.getType()));
            gc.gridx = 2;
            gc.gridy = y;
            add(type, gc);

            JLabel mass = new JLabel(String.valueOf(((int) f.getMass())));
            gc.gridx = 3;
            gc.gridy = y;
            add(mass, gc);

            y += 1;
        }
    }

    private String typeToString(FoodTypes type) {
        if (type.equals(FoodTypes.VEGETABLES_AND_FRUITS)) {
            return "Vegetables and Fruits";
        } else if (type.equals(FoodTypes.GRAINS)) {
            return "Grains";
        } else if (type.equals(FoodTypes.PROTEINS)) {
            return "Proteins";
        } else if (type.equals(FoodTypes.OTHERS)) {
            return "Others";
        }
        return "";
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

        JLabel type = new JLabel("Type: ");
        gc.gridx = 2;
        gc.gridy = 0;
        add(type, gc);

        JLabel mass = new JLabel("Mass: ");
        gc.gridx = 3;
        gc.gridy = 0;
        add(mass, gc);
    }
}
