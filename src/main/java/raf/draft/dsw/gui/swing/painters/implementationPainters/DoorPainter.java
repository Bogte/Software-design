package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.*;

@Getter
public class DoorPainter extends BasicPainter {

    public DoorPainter(RoomElement door) {
        this.setRoomElement(door);
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
        double startX = elementX;
        double startY = elementY + elementHeight;
        double controlX1 = elementX + elementWidth / 4;
        double controlY1 = elementY + elementHeight / 4;
        double controlX2 = elementX + 3 * elementWidth / 4;
        double controlY2 = elementY;
        double endX = elementX + elementWidth;
        double endY = elementY;
        CubicCurve2D.Double curve = new CubicCurve2D.Double(startX, startY, controlX1, controlY1, controlX2, controlY2, endX, endY);
        this.setShape(curve);
        look.setPaint(Color.BLACK);
        look.draw(curve);
        Line2D.Double verticalLine = new Line2D.Double(endX, endY, endX, elementY + elementHeight);
        look.setPaint(Color.BLACK);
        look.draw(verticalLine);
        look.setTransform(originalTransform);
    }
}
