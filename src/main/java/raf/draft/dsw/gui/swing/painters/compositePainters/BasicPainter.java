package raf.draft.dsw.gui.swing.painters.compositePainters;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

@Getter
@Setter
public abstract class BasicPainter implements Painter {
    private Shape shape;
    private RoomElement roomElement;
    private double zoomFactor = 1.0;
    @Override
    public boolean elementAt(Point p) {
        return shape.contains(p);
    }
    public boolean elementAt(BasicPainter bp)
    {
        return shape.intersects(bp.getShape().getBounds2D());
    }


    public void rotate(int angle) {
        int newRotation = (this.getRoomElement().getRotation() + angle) % 360;
        if (newRotation < 0) {
            newRotation += 360;
        }
        this.getRoomElement().setRotation(newRotation);
    }




}

