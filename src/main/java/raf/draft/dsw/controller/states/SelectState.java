package raf.draft.dsw.controller.states;

import lombok.Getter;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
@Getter
public class SelectState implements State{
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Timer timer;
    @Override
    public void run(RoomView roomView,MouseEvent e) {
        List<Painter> painters=new ArrayList<>();
        painters.addAll(roomView.getSelectedPainters());
        roomView.getSelectedPainters().removeAll(painters);
        for (Painter p:roomView.getPainters())
        {
            if (p.elementAt(e.getPoint()) || roomView.getSelectRectangle().intersects(((BasicPainter)p).getShape().getBounds2D()))
            {
                roomView.getSelectedPainters().add(p);
            }
        }
        roomView.repaint();


    }

    @Override
    public void myMouseClick(MouseEvent e) {
    }

    @Override
    public void myMouseDrag(MouseEvent e) {
        endX=e.getX();
        endY=e.getY();
        (MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView().setSelectedRectangleNum(startX,startY,endX,endY);
        (MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView().repaint();
    }

    @Override
    public void myMousePressed(MouseEvent e) {
        startX=e.getX();
        startY=e.getY();
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
        endX=e.getX();
        endY=e.getY();
        stopTimer();
        run((MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView(),e);
        (MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView().setSelectedRectangleNum(0,0,0,0);
        (MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView().repaint();


    }
    private void startTimer() {
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView roomView = (MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView();
                roomView.repaint();
            }

        }
        );
        timer.start();
    }

        private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

}
