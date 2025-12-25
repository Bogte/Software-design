package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class Closet extends RoomElement implements Prototype {
    private static int closetNumber=0;

    public static int getClosetNumber() {
        return closetNumber;
    }

    public static void setClosetNumber(int closetNumber) {
        Closet.closetNumber = closetNumber;
    }

    public Closet(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super(("Closet"+closetNumber), parent, locationX,locationY, cmLenght, cmHeight, rotation);
        closetNumber++;
    }

    @Override
    public Prototype clone() {
        return new Closet(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Closet() {
        // Pozivanje super konstruktora sa null vrednostima
    }
}
