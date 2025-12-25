package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

@Getter
public class BoilerPainter extends BasicPainter {

    public BoilerPainter(RoomElement boiler) {
        this.setRoomElement(boiler);
    }

    @Override
    public void paint(Graphics2D look) {
        AffineTransform originalTransform = look.getTransform();
        double elementX = this.getRoomElement().getLocationX();
        double elementY = this.getRoomElement().getLocationY();
        double elementSize = this.getRoomElement().getCmLenght();
        double circleRadius = elementSize / 2;
        double centerX = elementX + circleRadius;
        double centerY = elementY + circleRadius;
        look.translate(centerX, centerY);
        look.scale(this.getZoomFactor(), this.getZoomFactor());
        look.translate(-centerX, -centerY);
        int rotation = this.getRoomElement().getRotation();
        look.rotate(Math.toRadians(rotation), centerX, centerY);
        Ellipse2D.Double circle = new Ellipse2D.Double(elementX, elementY, elementSize, elementSize);
        this.setShape(circle);
        look.setPaint(Color.BLACK);
        look.draw(circle);
        look.setPaint(Color.WHITE);
        look.fill(circle);
        double margin = 0.25 * elementSize;
        double xSize = elementSize / 2 - margin;
        double startX1 = elementX + circleRadius - xSize / 2;
        double startY1 = elementY + circleRadius - xSize / 2;
        double endX1 = elementX + circleRadius + xSize / 2;
        double endY1 = elementY + circleRadius + xSize / 2;
        double startX2 = elementX + circleRadius + xSize / 2;
        double startY2 = elementY + circleRadius - xSize / 2;
        double endX2 = elementX + circleRadius - xSize / 2;
        double endY2 = elementY + circleRadius + xSize / 2;

        Line2D.Double line1 = new Line2D.Double(startX1, startY1, endX1, endY1);
        look.setPaint(Color.BLACK);
        look.draw(line1);

        Line2D.Double line2 = new Line2D.Double(startX2, startY2, endX2, endY2);
        look.draw(line2);

        look.setTransform(originalTransform);
    }
}
