package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;
public class Door extends RoomElement implements Prototype {
    private static int doorNumber=0;

    public static int getDoorNumber() {
        return doorNumber;
    }

    public static void setDoorNumber(int doorNumber) {
        Door.doorNumber = doorNumber;
    }

    public Door(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super(("Door"+doorNumber), parent,locationX,locationY, cmLenght, cmHeight, rotation);
        doorNumber++;
    }

    @Override
    public Prototype clone() {
        return new Door(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Door() {
        // Pozivanje super konstruktora sa null vrednostima
    }
}
