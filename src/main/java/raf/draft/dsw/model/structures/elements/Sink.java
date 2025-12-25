package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class Sink extends RoomElement implements Prototype {
    private static int sinkNumber=0;

    public static int getSinkNumber() {
        return sinkNumber;
    }

    public static void setSinkNumber(int sinkNumber) {
        Sink.sinkNumber = sinkNumber;
    }

    public Sink(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super(("Sink"+sinkNumber), parent, locationX,locationY, cmLenght, cmHeight, rotation);
        sinkNumber++;
    }

    @Override
    public Prototype clone() {
        return new Sink(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Sink() {
        // Pozivanje super konstruktora sa null vrednostima
    }
}
