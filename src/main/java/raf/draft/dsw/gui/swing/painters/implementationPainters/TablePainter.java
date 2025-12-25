package raf.draft.dsw.gui.swing.painters.implementationPainters;

import lombok.Getter;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.model.nodes.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

@Getter
public class TablePainter extends BasicPainter {

    public TablePainter(RoomElement table) {
        this.setRoomElement(table);
    }

    @Override
    public void paint(Graphics2D look) {
        AffineTransform originalTransform = look.getTransform();
        double tableX = this.getRoomElement().getLocationX();
        double tableY = this.getRoomElement().getLocationY();
        double tableSize = this.getRoomElement().getCmLenght();
        double centerX = tableX + tableSize / 2;
        double centerY = tableY + tableSize / 2;
        look.translate(centerX, centerY);
        look.scale(this.getZoomFactor(), this.getZoomFactor());
        look.translate(-centerX, -centerY);
        int rotation = this.getRoomElement().getRotation();
        look.rotate(Math.toRadians(rotation), centerX, centerY);
        Rectangle2D.Double tableRectangle = new Rectangle2D.Double(tableX, tableY, tableSize, tableSize);
        this.setShape(tableRectangle);
        look.setPaint(Color.BLACK);
        look.draw(this.getShape());
        look.setPaint(Color.WHITE);
        look.fill(this.getShape());
        double pointRadius = tableSize * 0.05;
        double pointOffset = tableSize * 0.1;
        look.setPaint(Color.BLACK);
        look.fill(new Ellipse2D.Double(tableX + pointOffset - pointRadius / 2, tableY + pointOffset - pointRadius / 2, pointRadius, pointRadius));
        look.fill(new Ellipse2D.Double(tableX + tableSize - pointOffset - pointRadius / 2, tableY + pointOffset - pointRadius / 2, pointRadius, pointRadius));
        look.fill(new Ellipse2D.Double(tableX + pointOffset - pointRadius / 2, tableY + tableSize - pointOffset - pointRadius / 2, pointRadius, pointRadius));
        look.fill(new Ellipse2D.Double(tableX + tableSize - pointOffset - pointRadius / 2, tableY + tableSize - pointOffset - pointRadius / 2, pointRadius, pointRadius));
        look.setTransform(originalTransform);
    }
}
