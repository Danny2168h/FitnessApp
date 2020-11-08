package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccountMenu extends JPanel implements ActionListener {

    private static final int LABEL_X = 250;
    private static final int NAME_Y = 125;
    private static final int SEX_Y = NAME_Y + 75;
    private static final int WEIGHT_Y = SEX_Y + 75;

    private FitnessAppGUI fitnessAppGUI;
    private JTextField name;
    private JTextField weight;
    private JComboBox sex;
    private JButton submit;
    private JLabel negativeWeight;
    private JLabel invalidWeight;

    public CreateAccountMenu(FitnessAppGUI fitnessAppGUI) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setLayout(null);
        this.setSize(FitnessAppGUI.FRAME_WIDTH, FitnessAppGUI.FRAME_HEIGHT);
        this.setLocation(0, 0);
        this.setBackground(Color.LIGHT_GRAY);
        JLabel title = new JLabel("Create An Account Below");
        title.setFont(new Font("Calibri", Font.BOLD, 35));
        title.setBounds(210, 10, 600, 100);
        this.add(title);
        addLabels();
        addInputFields();
        submit = new JButton("Submit");
        submit.setBounds(435, WEIGHT_Y + 75, 90, 25);
        submit.addActionListener(this);
        this.add(submit);
    }

    private void addInputFields() {
        name = new JTextField();
        name.setBounds(LABEL_X + 155, NAME_Y - 5, 120, 25);

        String[] choices = {"Male", "Female"};
        sex = new JComboBox(choices);
        sex.setLayout(null);
        sex.setBounds(LABEL_X + 155, SEX_Y - 5, 60, 25);

        weight = new JTextField();
        weight.setBounds(LABEL_X + 155, WEIGHT_Y - 5, 120, 25);
        this.add(name);
        this.add(sex);
        this.add(weight);
    }

    private void addLabels() {
        makeLabels("First Name: ", LABEL_X, NAME_Y);
        makeLabels("Sex/Gender: ", LABEL_X, SEX_Y);
        makeLabels("Weight (KG): ", LABEL_X, WEIGHT_Y);
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
        String userInput = weight.getText();
        try {
            int i = Integer.parseInt(userInput);
            if (i <= 0) {
                removeErrorLabel(invalidWeight);
                if (!checkComponentContains(negativeWeight)) {
                    negativeWeight = new JLabel("Please enter weight > 0");
                    negativeWeight.setBounds(280, WEIGHT_Y + 40, 150, 20);
                    this.add(negativeWeight);
                }
            } else {
                nextSteps();
            }
        } catch (NumberFormatException ex) {
            removeErrorLabel(negativeWeight);
            if (!checkComponentContains(invalidWeight)) {
                invalidWeight = new JLabel("Please enter whole number for weight");
                invalidWeight.setBounds(280, WEIGHT_Y + 40, 250, 20);
                this.add(invalidWeight);
            }
        }
        this.revalidate();
        this.repaint();
    }

    private void nextSteps() {
        String strWeight = weight.getText();
        int weight = Integer.parseInt(strWeight);
        String strName = name.getText();
        int sexSelectedIndex = sex.getSelectedIndex();
        System.out.println("point a");
        boolean sex = true;
        if (sexSelectedIndex == 1) {
            System.out.println("female");
            sex = false;
        }
        fitnessAppGUI.afterCreateAccount(weight, sex, strName);
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

