package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class Bathtub extends RoomElement implements Prototype {
    private static int bathtubNumber=0;

    public static int getBathtubNumber() {
        return bathtubNumber;
    }

    public static void setBathtubNumber(int bathtubNumber) {
        Bathtub.bathtubNumber = bathtubNumber;
    }

    public Bathtub(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super(("Bathub"+bathtubNumber), parent, locationX,locationY, cmLenght, cmHeight, rotation);
        bathtubNumber++;
    }

    @Override
    public Prototype clone() {
        return new Bathtub(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Bathtub() {
        // Pozivanje super konstruktora sa null vrednostima
    }
}
