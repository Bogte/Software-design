package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class UndoAction extends AbstractRoomAction{
    public UndoAction(){
        putValue(SMALL_ICON, loadIcon("/images/Undo.jpg"));
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ApplicationFramework.getInstance().getCommandManager().undoCommand();
    }
}
