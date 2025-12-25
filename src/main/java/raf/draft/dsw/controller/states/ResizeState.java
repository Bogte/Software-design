package raf.draft.dsw.controller.states;

import raf.draft.dsw.controller.commands.implementation.ResizeCommand;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class ResizeState implements State{
    private RoomView roomView;
    private RoomElement selectedElement = null;
    private int startX, startY;
    private int startWidth, startHeight;

    private boolean isResizing = false;
    @Override
    public void run(RoomView roomView, MouseEvent e) {
        this.roomView = roomView;
        for (Painter painter : roomView.getPainters()) {
            if (((BasicPainter)painter).getShape().contains(e.getPoint())) {
                selectedElement = ((BasicPainter) painter).getRoomElement();
                isResizing = true;
                startX = e.getX();
                startY = e.getY();
                startWidth = selectedElement.getCmLenght();
                startHeight = selectedElement.getCmHeight();
                break;
            }
        }
    }

    @Override
    public void myMouseClick(MouseEvent e) {

    }

    @Override
    public void myMouseDrag(MouseEvent e) {
        if (isResizing && selectedElement != null) {
            int deltaX = e.getX() - startX;
            int deltaY = e.getY() - startY;
            int newWidth = startWidth + deltaX;
            int newHeight = startHeight + deltaY;
            ResizeCommand resizeCommand=new ResizeCommand(startWidth,startHeight,newWidth,newHeight,selectedElement);
            ApplicationFramework.getInstance().getCommandManager().addCommand(resizeCommand);
            selectedElement.setCmLenght(newWidth);
            selectedElement.setCmHeight(newHeight);
            roomView.repaint();
        }
    }

    @Override
    public void myMousePressed(MouseEvent e) {
        run(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView(),e);
    }

    @Override
    public void myKeyPressed(KeyEvent e) {

    }

    @Override
    public void myMouseWheelMoved(MouseWheelEvent e) {

    }

    @Override
    public void MyMouseRelease(MouseEvent e) {
        isResizing = false;
    }
}
