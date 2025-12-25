package raf.draft.dsw.model.nodes;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public abstract class RoomElement extends DraftNode {
    private int locationX;
    private int locationY;
    private int locationStartX;
    private int locationStartY;
    private int cmLenght;
    private int cmHeight;
    private int rotation;

    public RoomElement(String name, DraftNode parent,int locationX,int locationY,int cmLenght,int cmHeight,int rotation) {
        super(name, parent);
        this.locationX=locationX;
        this.locationY=locationY;
        this.locationStartX=locationX;
        this.locationStartY=locationY;
        this.cmLenght=cmLenght;
        this.cmHeight=cmHeight;
        this.rotation=rotation;
    }
    public RoomElement() {
        super(null, null); // Pozivanje super konstruktora sa null vrednostima
    }
}
