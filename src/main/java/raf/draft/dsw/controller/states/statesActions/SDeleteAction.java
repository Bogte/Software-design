package raf.draft.dsw.controller.states.statesActions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.commands.implementation.DeleteCommand;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class SDeleteAction extends AbstractRoomAction {
    public SDeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/delete.png"));
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "Delete Element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
            for (Painter p : roomView.getSelectedPainters()) {
                MainFrame.getInstance().getDraftTree().setSelectedNode(((BasicPainter) p).getRoomElement());
                //MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
            }
            if (!(roomView.getSelectedPainters().isEmpty())) {
                //roomView.getPainters().removeAll(roomView.getSelectedPainters());
                List<BasicPainter> basicPainters=new ArrayList<>();
                for (Painter p: roomView.getSelectedPainters())
                {
                    basicPainters.add((BasicPainter) p);
                }
                DeleteCommand deleteCommand=new DeleteCommand(basicPainters);
                ApplicationFramework.getInstance().getCommandManager().addCommand(deleteCommand);
            }


            roomView.getStateManager().setDeleteState();
            roomView.repaint();
        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Ne postoji nijedna soba");
        }

    }
}
