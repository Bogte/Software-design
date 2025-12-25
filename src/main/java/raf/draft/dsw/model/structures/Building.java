package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.lang.reflect.Parameter;
import java.util.Objects;
import java.util.Random;
@Getter
public class Building extends DraftNodeComposite {
    @JsonIgnore
    private Random rnd;
    @JsonIgnore
    private Color color;
    private int c1;
    private int c2;
    private int c3;
    public Building(String name,DraftNode parent) {
        super(name, parent);
        rnd=new Random();
        c1=rnd.nextInt(256);
        c2=rnd.nextInt(256);
        c3=rnd.nextInt(256);
        color=new Color(c1,c2,c3);
    }
    public Building() {
        super(null, null); // Pozivanje super konstruktora sa null vrednostima
        color=new Color(c1,c2,c3);
    }

    @Override
    public void addChild(DraftNode child) {
        if (child != null && child instanceof Room) {
            Room room = (Room) child;
            if (!this.getChildren().contains(room)) {
                this.getChildren().add(room);
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Building building = (Building) obj;
        return Objects.equals(this.getName(), building.getName());

    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getParent(), color);
    }
}
