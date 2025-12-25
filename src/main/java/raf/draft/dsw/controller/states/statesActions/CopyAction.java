package raf.draft.dsw.controller.states.statesActions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CopyAction extends AbstractRoomAction {
    public CopyAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/new-document.png"));
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "Copy");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            RoomView roomView= MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
            for (Painter p: roomView.getSelectedPainters())
            {
                roomView.getItems().add((Prototype)((BasicPainter)p).getRoomElement());
            }

        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Ne postoji nijedna soba");
        }

    }
}
