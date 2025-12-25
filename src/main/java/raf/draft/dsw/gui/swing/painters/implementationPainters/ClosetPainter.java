package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Getter
public class ClosetPainter extends BasicPainter {

    public ClosetPainter(RoomElement closet) {
        this.setRoomElement(closet);
    }

    @Override
    public void paint(Graphics2D look) {
        AffineTransform originalTransform = look.getTransform();
        double wardrobeX = this.getRoomElement().getLocationX();
        double wardrobeY = this.getRoomElement().getLocationY();
        double wardrobeWidth = this.getRoomElement().getCmLenght();
        double wardrobeHeight = this.getRoomElement().getCmHeight();
        double centerX = wardrobeX + wardrobeWidth / 2;
        double centerY = wardrobeY + wardrobeHeight / 2;
        look.translate(centerX, centerY);
        look.scale(this.getZoomFactor(), this.getZoomFactor());
        look.translate(-centerX, -centerY);
        int rotation = this.getRoomElement().getRotation();
        look.rotate(Math.toRadians(rotation), centerX, centerY);
        Rectangle2D.Double wardrobeRectangle = new Rectangle2D.Double(wardrobeX, wardrobeY, wardrobeWidth, wardrobeHeight);
        this.setShape(wardrobeRectangle);
        look.setPaint(Color.BLACK);
        look.draw(this.getShape());
        look.setPaint(Color.WHITE);
        look.fill(this.getShape());
        double lineX = wardrobeX + (wardrobeWidth / 2);
        look.setPaint(Color.BLACK);
        look.drawLine((int) lineX, (int) wardrobeY, (int) lineX, (int) (wardrobeY + wardrobeHeight));
        double pointRadius = wardrobeWidth * 0.05;
        double pointOffset = wardrobeHeight * 0.1;
        look.setPaint(Color.BLACK);
        look.fill(new Ellipse2D.Double(lineX - pointOffset - pointRadius / 2, wardrobeY + wardrobeHeight / 2 - pointRadius / 2, pointRadius, pointRadius));
        look.fill(new Ellipse2D.Double(lineX + pointOffset - pointRadius / 2, wardrobeY + wardrobeHeight / 2 - pointRadius / 2, pointRadius, pointRadius));
        look.setTransform(originalTransform);
    }
}
