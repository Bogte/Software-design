package raf.draft.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.actions.ChangeFileInfoAction;
import raf.draft.dsw.controller.actions.ChangingFileInfoAction;
import raf.draft.dsw.controller.actions.CreateFileAction;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
@Getter
@Setter
public class ChangeFileInfoPanel extends JFrame {
    private JTextField tf1;
    private JTextField tf2;
    private JTextField tf3;
    public ChangeFileInfoPanel() {
        initialize();
    }

    private void initialize(){

        setTitle("Change Info");
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

        JButton bt = new JButton();
        bt.setAction(new ChangingFileInfoAction(this));
        bt.setText("Change");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        jp.add(bt, gbc);
        if (!(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project))
        {
            tf1.setEditable(false);
            tf2.setEditable(false);
        }
        add(jp, BorderLayout.CENTER);
    }
}
