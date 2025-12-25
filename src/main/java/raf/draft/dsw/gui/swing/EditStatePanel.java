package raf.draft.dsw.gui.swing;

import lombok.Getter;
import raf.draft.dsw.controller.actions.ChangingFileInfoAction;
import raf.draft.dsw.controller.states.statesActions.SEditAction;
import raf.draft.dsw.controller.states.statesActions.SEditStateStart;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.*;
@Getter
public class EditStatePanel extends JFrame {
    private JTextField tf1;
    private JTextField tf2;
    private JButton bt;

    public EditStatePanel() {
        initialize();
    }

    private void initialize(){

        setTitle("Change Room Element Info");
        setLocationRelativeTo(null);

        JPanel jp = new JPanel();
        setSize(300, 300);
        jp.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        jp.add(new JLabel("Duzina elementa"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        tf1 = new JTextField(10);
        tf1.setEditable(true);
        jp.add(tf1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        jp.add(new JLabel("Sirina Elementa"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        tf2 = new JTextField(10);
        tf2.setEditable(true);
        jp.add(tf2, gbc);

        bt = new JButton();
        bt.setAction(new SEditStateStart());
        bt.setText("Change");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        jp.add(bt,gbc);
        add(jp, BorderLayout.CENTER);

    }
}
