package raf.draft.dsw.model.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.tab.SingleTab;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.RoomElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Getter
@Setter
public class Room extends DraftNodeComposite {
    private double cmLenght;
    private double cmHeight;
    @JsonIgnore private List<RoomElement> roomElements;

    public Room(String name,DraftNode parent) {
        super(name,parent);
        roomElements=new ArrayList<>();
    }
    public Room() {
        super(null, null);
    }

    @Override
    public void addChild(DraftNode child) {
        if (child != null && child instanceof RoomElement) {
            RoomElement roomELement = (RoomElement) child;
            if (!this.getChildren().contains(roomELement)) {
                this.getChildren().add(roomELement);
                //roomElements.add(roomELement);
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Room room = (Room) obj;
        return Objects.equals(this.getName(), room.getName()) &&
                Objects.equals(this.getParent(), room.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getParent());
    }

}
