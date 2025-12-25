package raf.draft.dsw.controller.states.statesActions;

import lombok.Getter;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.commands.implementation.CopyPasteCommand;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.factories.PainterFactory;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.RoomElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PasteAction extends AbstractRoomAction {
    public PasteAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/copy-two-paper-sheets-interface-symbol.png"));//postaviti ikonicu
        putValue(NAME, "");
        putValue(SHORT_DESCRIPTION, "Paste");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            CopyPasteCommand copyPasteCommand=new CopyPasteCommand();
            ApplicationFramework.getInstance().getCommandManager().addCommand(copyPasteCommand);
        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Ne postoji nijedna soba");
        }
    }
}
