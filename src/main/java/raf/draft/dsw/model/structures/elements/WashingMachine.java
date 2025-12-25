package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class WashingMachine extends RoomElement implements Prototype {
    private static int washingMachineNumber=0;

    public static int getWashingMachineNumber() {
        return washingMachineNumber;
    }

    public static void setWashingMachineNumber(int washingMachineNumber) {
        WashingMachine.washingMachineNumber = washingMachineNumber;
    }

    public WashingMachine(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super("WashingMachine"+washingMachineNumber, parent,locationX,locationY, cmLenght, cmHeight, rotation);
        washingMachineNumber++;
    }

    @Override
    public Prototype clone() {
        return new WashingMachine(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public WashingMachine() {
        // Pozivanje super konstruktora sa null vrednostima
    }
}
