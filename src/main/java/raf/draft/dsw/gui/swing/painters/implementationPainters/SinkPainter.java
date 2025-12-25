package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

@Getter
public class SinkPainter extends BasicPainter {

    public SinkPainter(RoomElement sink) {
        this.setRoomElement(sink);
    }

    @Override
    public void paint(Graphics2D look) {
        AffineTransform originalTransform = look.getTransform();
        double elementX = this.getRoomElement().getLocationX();
        double elementY = this.getRoomElement().getLocationY();
        double elementWidth = this.getRoomElement().getCmLenght();
        double elementHeight = this.getRoomElement().getCmHeight();
        double centerX = elementX + elementWidth / 2;
        double centerY = elementY + elementHeight / 2;
        look.translate(centerX, centerY);
        look.scale(this.getZoomFactor(), this.getZoomFactor());
        look.translate(-centerX, -centerY);
        int rotation = this.getRoomElement().getRotation();
        look.rotate(Math.toRadians(rotation), centerX, centerY);
        double[] xPoints = {elementX, elementX + elementWidth, elementX + elementWidth / 2};
        double[] yPoints = {elementY, elementY, elementY + elementHeight};
        Polygon triangle = new Polygon();
        for (int i = 0; i < 3; i++) {
            triangle.addPoint((int) xPoints[i], (int) yPoints[i]);
        }
        this.setShape(triangle);
        look.setPaint(Color.BLACK);
        look.draw(triangle);
        look.setPaint(Color.WHITE);
        look.fill(triangle);
        look.setPaint(Color.RED);
        look.fill(new Ellipse2D.Double(centerX - 5, centerY - 5, 10, 10));
        look.setTransform(originalTransform);
    }
}
