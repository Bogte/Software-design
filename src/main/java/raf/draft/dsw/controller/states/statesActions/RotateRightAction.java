package raf.draft.dsw.controller.states.statesActions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.commands.implementation.RotateLeftCommand;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.RoomElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;

public class RotateRightAction extends AbstractRoomAction {


    public RotateRightAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/rotating-arrow-to-the-right.png"));
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "Rotate Element Right");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
            RotateLeftCommand rotateLeftCommand=new RotateLeftCommand(roomView.getSelectedPainters());
            ApplicationFramework.getInstance().getCommandManager().addCommand(rotateLeftCommand);
        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Ne postoji nijedna soba");
        }

    }
}
