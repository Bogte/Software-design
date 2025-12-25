package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Getter
public class WashingMachinePainter extends BasicPainter {

    public WashingMachinePainter(RoomElement washingMachine) {
        this.setRoomElement(washingMachine);
    }

    @Override
    public void paint(Graphics2D look) {
        AffineTransform originalTransform = look.getTransform();
        int rotation = this.getRoomElement().getRotation();
        double elementX = this.getRoomElement().getLocationX();
        double elementY = this.getRoomElement().getLocationY();
        double elementSize = this.getRoomElement().getCmLenght();
        double centerX = elementX + elementSize / 2;
        double centerY = elementY + elementSize / 2;
        look.translate(centerX, centerY);
        look.scale(this.getZoomFactor(), this.getZoomFactor());
        look.translate(-centerX, -centerY);
        look.rotate(Math.toRadians(rotation), centerX, centerY);
        Rectangle2D.Double square = new Rectangle2D.Double(elementX, elementY, elementSize, elementSize);
        this.setShape(square);
        look.setPaint(Color.BLACK);
        look.draw(square);
        look.setPaint(Color.WHITE);
        look.fill(square);
        double ellipseWidth = elementSize * 0.6;
        double ellipseHeight = elementSize * 0.4;
        double ellipseX = elementX + (elementSize - ellipseWidth) / 2;
        double ellipseY = elementY + (elementSize - ellipseHeight) / 2;
        Ellipse2D.Double ellipse = new Ellipse2D.Double(ellipseX, ellipseY, ellipseWidth, ellipseHeight);
        look.setPaint(Color.BLACK);
        look.draw(ellipse);
        look.setPaint(Color.WHITE);
        look.fill(ellipse);
        look.setTransform(originalTransform);
    }
}
