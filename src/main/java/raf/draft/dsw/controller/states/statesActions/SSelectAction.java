package raf.draft.dsw.controller.states.statesActions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SSelectAction extends AbstractRoomAction {
    public SSelectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/touchscreen.png"));//postaviti ikonicu
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "Select Element");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            (MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView().getStateManager().setSelectState();
        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Ne postoji nijedna soba");
        }
    }
}
