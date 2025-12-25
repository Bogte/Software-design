package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class Boiler extends RoomElement implements Prototype {
    private static int boilerNumber=0;

    public static int getBoilerNumber() {
        return boilerNumber;
    }

    public static void setBoilerNumber(int boilerNumber) {
        Boiler.boilerNumber = boilerNumber;
    }

    public Boiler(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super(("Boiler"+boilerNumber), parent, locationX,locationY, cmLenght, cmHeight, rotation);
        boilerNumber++;
    }

    @Override
    public Prototype clone() {
        return new Boiler(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Boiler() {
      // Pozivanje super konstruktora sa null vrednostima
    }
}
