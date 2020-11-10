package ui.menus;

import ui.FitnessAppGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the popup window that prompts users to confirm the save
public class ConfirmSave extends JDialog implements ActionListener {

    private FitnessAppGUI fitnessAppGUI;

    //EFFECTS: Creates a new confirm save pop up window
    public ConfirmSave(FitnessAppGUI fitnessAppGUI) {
        this.fitnessAppGUI = fitnessAppGUI;
        this.setBounds(0, 0, 300, 200);
        this.setTitle("Confirm Save");
        this.setResizable(false);
        this.setVisible(true);
        JButton button = new JButton("Confirm");
        button.setBounds(105, 90, 90, 25);
        button.addActionListener(this);
        this.add(button);
        JLabel message1 = new JLabel("Saving overwrites previous data");
        message1.setBounds(55,30, 190, 25);
        JLabel message2 = new JLabel("Press button to confirm save");
        message2.setBounds(65,55, 190, 25);
        this.add(message1);
        this.add(message2);
    }


    @Override
    //MODIFIES: this
    // EFFECTS: processes button press, disposes of dialog window then updates person field in FitnessAppGUI
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        fitnessAppGUI.afterSaveState();
    }
}
