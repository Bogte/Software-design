package raf.draft.dsw.model.structures.elements;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;

public class Table extends RoomElement implements Prototype {
    private static int tableNumber=0;

    public static int getTableNumber() {
        return tableNumber;
    }

    public static void setTableNumber(int tableNumber) {
        Table.tableNumber = tableNumber;
    }

    public Table(DraftNode parent, int locationX, int locationY, int cmLenght, int cmHeight, int rotation) {
        super("Table"+tableNumber, parent,locationX,locationY, cmLenght, cmHeight, rotation);
        tableNumber++;
    }

    @Override
    public Prototype clone() {
        return new Table(this.getParent(),this.getLocationX(),this.getLocationY(),this.getCmLenght(),this.getCmHeight(),this.getRotation());
    }
    public Table() {
        // Pozivanje super konstruktora sa null vrednostima
    }
}
