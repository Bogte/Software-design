package raf.draft.dsw.controller.states;

import lombok.NoArgsConstructor;
import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

@NoArgsConstructor
public class EditRoomState implements State{

    @Override
    public void run(RoomView roomView,MouseEvent e) {

        String width = JOptionPane.showInputDialog(roomView, "Unesite širinu sobe (u cm):");
        String height = JOptionPane.showInputDialog(roomView, "Unesite visinu sobe (u cm):");
        try {
            double roomWidth = Double.parseDouble(width);
            double roomHeight = Double.parseDouble(height);
            roomView.setRoomDimensions(roomWidth, roomHeight);
            roomView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            roomView.repaint();
        } catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Dimenzije sobe nisu dobro unete");
        }
    }

    @Override
    public void myMouseClick(MouseEvent e) {
        run((MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView(),e);
    }

    @Override
    public void myMouseDrag(MouseEvent e) {
        return;
    }

    @Override
    public void myMousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void myKeyPressed(KeyEvent e) {
        return;
    }

    @Override
    public void myMouseWheelMoved(MouseWheelEvent e) {
        return;
    }

    @Override
    public void MyMouseRelease(MouseEvent e) {
        return;
    }
}
