package raf.draft.dsw.controller.states;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;

import raf.draft.dsw.gui.swing.tab.RoomView;

import java.awt.event.*;


public class ZoomState implements State {
    private static final double MIN_ZOOM = 0.1;
    private static final double MAX_ZOOM = 3.0;
    private double zoomFactor = 1.0;

    @Override
    public void run(RoomView roomView, MouseEvent e) {}

    @Override
    public void myMouseClick(MouseEvent e) {}

    @Override
    public void myMouseDrag(MouseEvent e) {}

    @Override
    public void myMousePressed(MouseEvent e) {}

    @Override
    public void myKeyPressed(KeyEvent e) {}

    @Override
    public void myMouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() < 0) {
            zoomIn();
        } else {
            zoomOut();
        }
        updateZoom();
    }

    @Override
    public void MyMouseRelease(MouseEvent e) {}

    private void zoomIn() {
        if (zoomFactor < MAX_ZOOM) {
            zoomFactor += 0.1;
        }
    }

    private void zoomOut() {
        if (zoomFactor > MIN_ZOOM) {
            zoomFactor -= 0.1;
        }
    }

    private void updateZoom() {
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        for (Painter painter : roomView.getPainters()) {

                ((BasicPainter) painter).setZoomFactor(zoomFactor);

        }
        roomView.repaint();
    }

}
