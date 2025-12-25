package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RedoAction extends AbstractRoomAction{
    public RedoAction(){
        putValue(SMALL_ICON, loadIcon("/images/Redo.jpg"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getCommandManager().doCommand();
    }
}
