package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewFoodMenu extends JPanel {

    private static final int BUTTON_WIDTH = 175;
    private static final int BUTTON_HEIGHT = 40;
    private static final int TOP_OFFSET = 140;
    private static final int GAP = 20;
    private static final int CENTER_BUTTON = FitnessAppGUI.FRAME_WIDTH / 2 - BUTTON_WIDTH / 2;

    private JButton breakfast;
    private JButton lunch;
    private JButton dinner;
    private JButton snacks;
    private JButton returnMain;

    private FitnessAppGUI fitnessAppGUI;

    public ViewFoodMenu(FitnessAppGUI fitnessAppGUI) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setLayout(null);
        this.setSize(FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setBackground(Color.lightGray);
        JLabel title = new JLabel("Select a Meal To View Foods");
        title.setBounds(255, 80, 300, 50);
        title.setFont(new Font("Calibri", Font.BOLD, 25));
        this.add(title);
        addButton1();
        addButton2();
        addButton3();
        addButton4();
        addReturnMain();
    }

    private void addReturnMain() {
        returnMain = new JButton("Return to Main Menu");
        returnMain.addActionListener(new ReturnMainMenuClickHandler());
        returnMain.setBounds(CENTER_BUTTON, TOP_OFFSET + 4
                * GAP + 4 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT - 10);
        returnMain.setFont(new Font("Calibri", Font.BOLD, 15));
        this.add(returnMain);
    }

    private void addButton1() {
        breakfast = new JButton("Breakfast");
        breakfast.addActionListener(new BreakfastClickHandler());
        breakfast.setBounds(CENTER_BUTTON, TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT);
        breakfast.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(breakfast);
    }

    private void addButton2() {
        lunch = new JButton("Lunch");
        lunch.addActionListener(new LunchClickHandler());
        lunch.setBounds(CENTER_BUTTON, TOP_OFFSET + GAP + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        lunch.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(lunch);
    }

    private void addButton3() {
        dinner = new JButton("Dinner");
        dinner.addActionListener(new DinnerClickHandler());
        dinner.setBounds(CENTER_BUTTON, TOP_OFFSET + 2 * GAP + 2 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        dinner.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(dinner);
    }

    private void addButton4() {
        snacks = new JButton("Snacks");
        snacks.addActionListener(new SnacksClickHandler());
        snacks.setBounds(CENTER_BUTTON, TOP_OFFSET + 3 * GAP + 3 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        snacks.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(snacks);
    }


    private class BreakfastClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: opens pop up window to display breakfast foods
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.displayFoods(0);
        }

    }

    private class LunchClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: opens up pop up window to display lunch foods
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.displayFoods(1);
        }

    }

    private class DinnerClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: opens up pop up window to display dinner foods
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.displayFoods(2);
        }

    }

    private class SnacksClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: opens up pop up window to display snack foods
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.displayFoods(3);
        }
    }

    private class ReturnMainMenuClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: opens up pop up window to display snack foods
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.returnToMainMenuFromViewFoods();
        }
    }

}