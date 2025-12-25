package raf.draft.dsw.controller.states.statesActions;

import lombok.Getter;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.EditStatePanel;
import raf.draft.dsw.model.messages.MessageType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class SEditAction {
    @Getter
    private EditStatePanel editStatePanel;

    public void actionPerformed() {
        try {
            editStatePanel = new EditStatePanel();
            editStatePanel.setVisible(true);
        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Ne postoji nijedna soba");
        }
    }
}
