package raf.draft.dsw.gui.swing.painters.compositePainters;

import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;

public interface Painter {
    void paint(Graphics2D look);
    boolean elementAt(Point p);
}
