package raf.draft.dsw.controller.states;

import raf.draft.dsw.gui.swing.tab.RoomView;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State {
    void run(RoomView roomView,MouseEvent e);
    void myMouseClick(MouseEvent e);
    void myMouseDrag(MouseEvent e);
    void myMousePressed(MouseEvent e);
    void myKeyPressed(KeyEvent e);
    void myMouseWheelMoved(MouseWheelEvent e);
    void MyMouseRelease(MouseEvent e);
}
