package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Getter
public class BathtubPainter extends BasicPainter {

     // Početni zoom faktor

    public BathtubPainter(RoomElement bathtub) {
        this.setRoomElement(bathtub);
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
        Rectangle2D.Double rectangle = new Rectangle2D.Double(this.getRoomElement().getLocationX(), this.getRoomElement().getLocationY(), this.getRoomElement().getCmLenght(), this.getRoomElement().getCmHeight());
        this.setShape(rectangle);
        look.setPaint(Color.BLACK);
        look.draw(this.getShape());
        look.setPaint(Color.WHITE);
        look.fill(this.getShape());
        double ellipseX = rectangle.getX() + (rectangle.getWidth() * 0.1);
        double ellipseY = rectangle.getY() + (rectangle.getHeight() * 0.1);
        double ellipseWidth = rectangle.getWidth() * 0.8;
        double ellipseHeight = rectangle.getHeight() * 0.8;
        look.setPaint(Color.BLACK);
        look.draw(new Ellipse2D.Double(ellipseX, ellipseY, ellipseWidth, ellipseHeight));
        look.setPaint(Color.WHITE);
        look.fill(new Ellipse2D.Double(ellipseX, ellipseY, ellipseWidth, ellipseHeight));
        look.setTransform(originalTransform);
    }

}
