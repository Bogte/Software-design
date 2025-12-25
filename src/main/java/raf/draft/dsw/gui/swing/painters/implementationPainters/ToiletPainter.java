package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.*;

@Getter
public class ToiletPainter extends BasicPainter {

    public ToiletPainter(RoomElement toilet) {
        this.setRoomElement(toilet);
    }

    @Override
    public void paint(Graphics2D look) {
        AffineTransform originalTransform = look.getTransform();
        int rotation = this.getRoomElement().getRotation();
        double elementX = this.getRoomElement().getLocationX();
        double elementY = this.getRoomElement().getLocationY();
        double elementWidth = this.getRoomElement().getCmLenght();
        double elementHeight = this.getRoomElement().getCmHeight();
        double centerX = elementX + elementWidth / 2;
        double centerY = elementY + elementHeight / 2;
        look.translate(centerX, centerY);
        look.scale(this.getZoomFactor(), this.getZoomFactor());
        look.translate(-centerX, -centerY);
        look.rotate(Math.toRadians(rotation), centerX, centerY);
        look.drawRect((int) elementX, (int) elementY, (int) elementWidth, (int) (elementHeight / 4));
        look.drawArc((int) elementX, (int) (elementY - elementHeight / 4), (int) elementWidth, (int) elementHeight, 180, 180);
        look.draw(new Ellipse2D.Double(elementX + elementWidth / 3, elementY + elementHeight / 3, elementWidth / 3, elementHeight / 3));
        Rectangle2D.Double shape = new Rectangle2D.Double(elementX, elementY, elementWidth, elementHeight);
        this.setShape(shape);
        look.setTransform(originalTransform);
    }
}
