package raf.draft.dsw.controller.states;

import lombok.Getter;
import raf.draft.dsw.controller.commands.CommandManager;
import raf.draft.dsw.controller.commands.implementation.MoveCommand;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.RoomElement;
import javax.swing.*;
import java.awt.event.*;

@Getter
public class MoveState implements State {
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Timer timer;

    @Override
    public void run(RoomView roomView, MouseEvent e) {
        if (roomView.getSelectedPainters().isEmpty()) {
            roomView.getSelectedPainters().addAll(roomView.getPainters());
        }
        int deltaX = e.getX() - startX;
        int deltaY = e.getY() - startY;
        for (Painter painter : roomView.getSelectedPainters()) {
            BasicPainter basicPainter = (BasicPainter) painter;
            basicPainter.getRoomElement().setLocationX(basicPainter.getRoomElement().getLocationX() + deltaX);
            basicPainter.getRoomElement().setLocationY(basicPainter.getRoomElement().getLocationY() + deltaY);

        }
        checkSnappingToEdges(roomView);
        if (isItCut(roomView)) {
            oldPositions();
        }
        roomView.repaint();
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void myMouseClick(MouseEvent e) {
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.getSelectedPainters().clear();
        for (Painter p : roomView.getPainters()) {
            if (p.elementAt(e.getPoint()) || roomView.getSelectRectangle().intersects(((BasicPainter) p).getShape().getBounds2D())) {
                roomView.getSelectedPainters().add(p);
            }
        }
        roomView.repaint();
    }

    @Override
    public void myMouseDrag(MouseEvent e) {
        int deltaX = e.getX() - startX;
        int deltaY = e.getY() - startY;

        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        for (Painter painter : roomView.getSelectedPainters()) {
            BasicPainter basicPainter = (BasicPainter) painter;
            RoomElement element = basicPainter.getRoomElement();
            element.setLocationX(element.getLocationX() + deltaX);
            element.setLocationY(element.getLocationY() + deltaY);
        }
        checkSnappingToEdges(roomView);
        roomView.repaint();
        startX = e.getX();
        startY = e.getY();
    }

    @Override
    public void myMousePressed(MouseEvent e) {
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        if (roomView.getSelectedPainters().isEmpty()) {
            roomView.getSelectedPainters().addAll(roomView.getPainters());
        }
        startX = e.getX();
        startY = e.getY();
        startTimer();
    }

    @Override
    public void myKeyPressed(KeyEvent e) {
    }

    @Override
    public void myMouseWheelMoved(MouseWheelEvent e) {
        return;
    }

    @Override
    public void MyMouseRelease(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        if (isItCut(roomView)) {
            oldPositions();
        } else {
            checkSnappingToEdges(roomView);
        }
        for (Painter painter : roomView.getSelectedPainters()) {
            MoveCommand moveCommand=new MoveCommand(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView());
            BasicPainter basicPainter = (BasicPainter) painter;
            moveCommand.getBasicPainters().add(basicPainter);
            RoomElement element = basicPainter.getRoomElement();
            moveCommand.getPositionXstart().add(element.getLocationStartX());
            moveCommand.getPositionYstart().add(element.getLocationStartY());
            element.setLocationStartX(element.getLocationX());
            element.setLocationStartY(element.getLocationY());
            moveCommand.getPositionXend().add(element.getLocationStartX());
            moveCommand.getPositionYend().add(element.getLocationStartY());
            ApplicationFramework.getInstance().getCommandManager().addCommand(moveCommand);
        }

        roomView.repaint();
        roomView.getSelectedPainters().clear();
    }

    private void startTimer() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
                roomView.repaint();
            }
        });
        timer.start();
    }

    private void checkSnappingToEdges(RoomView roomView) {
        for (Painter painter : roomView.getSelectedPainters()) {
            BasicPainter basicPainter = (BasicPainter) painter;
            RoomElement element = basicPainter.getRoomElement();

            if (element.getLocationX() + element.getCmLenght() > roomView.getWidth()) {
                element.setLocationX(roomView.getWidth() - (int) element.getCmLenght());
            }

            if (element.getLocationX() < 0) {
                element.setLocationX(0);
            }

            if (element.getLocationY() + element.getCmHeight() > roomView.getHeight()) {
                element.setLocationY(roomView.getHeight() - (int) element.getCmHeight());
            }

            if (element.getLocationY() < 0) {
                element.setLocationY(0);
            }

        }
    }

    private boolean isItCut(RoomView roomView) {
        for (Painter selectedPainter : roomView.getSelectedPainters()) {
            for (Painter painter : roomView.getPainters()) {
                if (selectedPainter != painter) {
                    BasicPainter selectedBasicPainter = (BasicPainter) selectedPainter;
                    BasicPainter basicPainter = (BasicPainter) painter;
                    if (selectedBasicPainter.elementAt(basicPainter)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void oldPositions() {
        for (Painter painter : MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView().getSelectedPainters()) {
            BasicPainter basicPainter = (BasicPainter) painter;
            RoomElement element = basicPainter.getRoomElement();
            element.setLocationX(element.getLocationStartX());
            element.setLocationY(element.getLocationStartY());

        }
    }
}
