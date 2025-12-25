package raf.draft.dsw.controller.states;

import raf.draft.dsw.controller.commands.implementation.DeleteCommand;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State{
    @Override
    public void run(RoomView roomView, MouseEvent e) {
        Painter deletePainter = null;
        for (Painter p:roomView.getPainters())
        {
            if (p.elementAt(e.getPoint()))
            {
                deletePainter=p;
            }
        }
        try {
            MainFrame.getInstance().getDraftTree().setSelectedNode(((BasicPainter) deletePainter).getRoomElement());
//            roomView.getPainters().remove(deletePainter);
            List<BasicPainter> deletePainters=new ArrayList<>();
            deletePainters.add((BasicPainter)deletePainter);
            DeleteCommand deleteCommand=new DeleteCommand(deletePainters);
            ApplicationFramework.getInstance().getCommandManager().addCommand(deleteCommand);
            //MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
            //roomView.repaint();
        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p){}

    }

    @Override
    public void myMouseClick(MouseEvent e) {
        run(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView(),e);
    }

    @Override
    public void myMouseDrag(MouseEvent e) {}

    @Override
    public void myMousePressed(MouseEvent e) {}

    @Override
    public void myKeyPressed(KeyEvent e) {}

    @Override
    public void myMouseWheelMoved(MouseWheelEvent e) {}

    @Override
    public void MyMouseRelease(MouseEvent e) {}
}
