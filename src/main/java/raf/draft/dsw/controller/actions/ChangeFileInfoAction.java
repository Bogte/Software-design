package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.ChangeFileInfoPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ChangeFileInfoAction extends AbstractRoomAction{

    public ChangeFileInfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/changefileicon.png"));
        putValue(NAME, "Change File");
        putValue(SHORT_DESCRIPTION, "Change File");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ChangeFileInfoPanel changeFileInfoPanel=new ChangeFileInfoPanel();
        changeFileInfoPanel.setVisible(true);

    }
}
