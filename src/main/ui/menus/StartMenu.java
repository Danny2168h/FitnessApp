package ui.menus;

import model.Person;
import persistence.JsonReader;
import ui.FitnessAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

//Represents the start menu of the program
public class StartMenu extends JPanel {

    private static final int BUTTON_WIDTH = 175;
    private static final int BUTTON_HEIGHT = 40;
    private static final int TOP_OFFSET = 140;
    private static final int GAP = 20;
    private static final int CENTER = FitnessAppGUI.FRAME_WIDTH / 2 - BUTTON_WIDTH / 2;

    private JButton acc1;
    private JButton acc2;
    private JButton acc3;

    private JsonReader jsonReader;

    private FitnessAppGUI fitnessAppGUI;

    //EFFECTS: Creates new start menu
    public StartMenu(FitnessAppGUI fitnessAppGUI) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setLayout(null);
        this.setSize(FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setBackground(Color.lightGray);
        JLabel title = new JLabel("Please Select an Account");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setBounds(214, 50, 375, 100);
        this.add(title);
        try {
            addButtonAcc1();
            addButtonAcc2();
            addButtonAcc3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //REQUIRES: adds button that represents account 1 to the panel
    private void addButtonAcc1() throws IOException {
        acc1 = new JButton(getAccountName("1"));
        acc1.addActionListener(new Acc1ClickHandler());
        acc1.setBounds(CENTER, TOP_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT);
        acc1.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(acc1);
    }

    //MODIFIES: this
    //REQUIRES: adds button that represents account 2 to the panel
    private void addButtonAcc2() throws IOException {
        acc2 = new JButton(getAccountName("2"));
        acc2.addActionListener(new Acc2ClickHandler());
        acc2.setBounds(CENTER, TOP_OFFSET + GAP + BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        acc2.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(acc2);
    }

    //MODIFIES: this
    //REQUIRES: adds button that represents account 3 to the panel
    private void addButtonAcc3() throws IOException {
        acc3 = new JButton(getAccountName("3"));
        acc3.addActionListener(new Acc3ClickHandler());
        acc3.setBounds(CENTER, TOP_OFFSET + 2 * GAP + 2 * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
        acc3.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(acc3);
    }

    //REQUIRES: Input to be 1,2 or 3
    //EFFECTS: Produces the name of the account
    private String getAccountName(String i) throws IOException {
        Boolean empty = true;
        Person p = null;
        if (i.equals("1")) {
            jsonReader = new JsonReader("./data/person1.json");
            empty = jsonReader.isEmptyFile();
        } else if (i.equals("2")) {
            jsonReader = new JsonReader("./data/person2.json");
            empty = jsonReader.isEmptyFile();
        } else {
            jsonReader = new JsonReader("./data/person3.json");
            empty = jsonReader.isEmptyFile();
        }
        if (empty) {
            return "Empty Account";
        } else {
            p = jsonReader.read();
            return p.getName() + "'s Account";
        }
    }

    private class Acc1ClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: Changes field accNumber in fitnessAPPGUI to reflect acc 1
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.afterAccountSelect(1);
        }

    }

    private class Acc2ClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: Changes field accNumber in fitnessAPPGUI to reflect acc 2
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.afterAccountSelect(2);
        }

    }

    private class Acc3ClickHandler implements ActionListener {
        // MODIFIES: FitnessAppGUI
        // EFFECTS: Changes field accNumber in fitnessAPPGUI to reflect acc 3
        //
        @Override
        public void actionPerformed(ActionEvent e) {
            fitnessAppGUI.afterAccountSelect(3);
        }

    }

}
