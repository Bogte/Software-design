package raf.draft.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.actions.CreateFileAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
@Getter
@Setter
public class NewProjectPanel extends JFrame{

    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    private JRadioButton r1;
    private JRadioButton r2;
    private JRadioButton r3;
    public NewProjectPanel() {
        initialize();
    }

    private void initialize(){

        setTitle("New File");
        setLocationRelativeTo(null);

        JPanel jp = new JPanel();
        setSize(300, 300);
        jp.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        jp.add(new JLabel("Autor's name: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        tf1 = new JTextField(10);
        tf1.setEditable(true);
        jp.add(tf1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        jp.add(new JLabel("File path: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        tf2 = new JTextField(10);
        tf2.setEditable(true);
        jp.add(tf2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        jp.add(new JLabel("Project name: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        tf3 = new JTextField(10);
        tf3.setEditable(true);
        jp.add(tf3, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        r1 = new JRadioButton("Project");
        jp.add(r1, gbc);
        r1.setSelected(true);

        gbc.gridx = 1;
        gbc.gridy = 3;
        r2 = new JRadioButton("Building");
        jp.add(r2, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        r3 = new JRadioButton("Room");
        jp.add(r3, gbc);

        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);
        ItemListener radioListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    boolean isProjectSelected = r1.isSelected();
                    tf1.setEditable(isProjectSelected);
                    tf1.setText("");
                    tf2.setEditable(isProjectSelected);
                    tf2.setText("");
                }
            }
        };

        r1.addItemListener(radioListener);
        r2.addItemListener(radioListener);
        r3.addItemListener(radioListener);


        JButton bt = new JButton();
        bt.setAction(new CreateFileAction(this));
        bt.setText("Create");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        jp.add(bt, gbc);


        add(jp, BorderLayout.CENTER);
    }

}
