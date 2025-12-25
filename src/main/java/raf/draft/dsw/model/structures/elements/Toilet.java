package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class Toilet extends RoomElement implements Prototype {
    private static int toiletNumber=0;

    public static int getToiletNumber() {
        return toiletNumber;
    }

    public static void setToiletNumber(int toiletNumber) {
        Toilet.toiletNumber = toiletNumber;
    }

    public Toilet(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super("Toilet"+toiletNumber, parent,locationX,locationY, cmLenght, cmHeight, rotation);
        toiletNumber++;
    }

    @Override
    public Prototype clone() {
        return new Toilet(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Toilet() {
        // Pozivanje super konstruktora sa null vrednostima
    }
}
