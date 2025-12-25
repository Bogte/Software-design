package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

@Getter
public class BedPainter extends BasicPainter {

    public BedPainter(RoomElement bed) {
        this.setRoomElement(bed);
    }

    @Override
    public void paint(Graphics2D look) {
        AffineTransform originalTransform = look.getTransform();
        double centerX = this.getRoomElement().getLocationX() + this.getRoomElement().getCmLenght() / 2;
        double centerY = this.getRoomElement().getLocationY() + this.getRoomElement().getCmHeight() / 2;
        look.translate(centerX, centerY);
        look.scale(this.getZoomFactor(), this.getZoomFactor());
        look.translate(-centerX, -centerY);
        int rotation = this.getRoomElement().getRotation();
        look.rotate(Math.toRadians(rotation), centerX, centerY);
        Rectangle2D.Double bedRectangle = new Rectangle2D.Double(this.getRoomElement().getLocationX(), this.getRoomElement().getLocationY(), this.getRoomElement().getCmLenght(), this.getRoomElement().getCmHeight());
        this.setShape(bedRectangle);
        look.setPaint(Color.BLACK);
        look.draw(this.getShape());
        look.setPaint(Color.WHITE);
        look.fill(this.getShape());
        double pillowX = bedRectangle.getX() + (bedRectangle.getWidth() * 0.1);
        double pillowY = bedRectangle.getY() + (bedRectangle.getHeight() * 0.1);
        double pillowWidth = bedRectangle.getWidth() * 0.8;
        double pillowHeight = bedRectangle.getHeight() * 0.3;
        look.setPaint(Color.LIGHT_GRAY);
        look.fill(new Rectangle2D.Double(pillowX, pillowY, pillowWidth, pillowHeight));
        look.setPaint(Color.BLACK);
        look.draw(new Rectangle2D.Double(pillowX, pillowY, pillowWidth, pillowHeight));
        look.setTransform(originalTransform);
    }
}
