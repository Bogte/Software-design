package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class Bed extends RoomElement implements Prototype {
    private static int bedNumber=0;

    public static int getBedNumber() {
        return bedNumber;
    }

    public static void setBedNumber(int bedNumber) {
        Bed.bedNumber = bedNumber;
    }

    public Bed(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super(("Bed"+bedNumber), parent, locationX,locationY, cmLenght, cmHeight, rotation);
        bedNumber++;
    }

    @Override
    public Prototype clone() {
        return new Bed(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Bed() {
         // Pozivanje super konstruktora sa null vrednostima
    }
}
