package raf.draft.dsw.model.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DraftNodeComposite.class, name = "composite"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.Room.class, name = "room"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.Building.class, name = "building"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.nodes.RoomElement.class, name = "element"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Bathtub.class, name = "bathtub"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Bed.class, name = "bed"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Sink.class, name = "sink"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Door.class, name = "doors"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Closet.class, name = "wardrobe"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Boiler.class, name = "boiler"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Table.class, name = "table"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.Toilet.class, name = "toiletBowl"),
        @JsonSubTypes.Type(value = raf.draft.dsw.model.structures.elements.WashingMachine.class, name = "washingMachine")
})
public abstract class DraftNode {
    @JsonIgnore
    private DraftNode parent;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private DraftNode building;
    @JsonProperty("parentId")
    private UUID parentId;

    private UUID id = UUID.randomUUID();

    public DraftNode(String name,DraftNode parent) {
        this.name = name;
        this.parent=parent;
        if (parent != null) {
            this.parentId = parent.getId();
        }
    }

    public DraftNode() {
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DraftNode draftNode = (DraftNode) o;
        return Objects.equals(getParent(), draftNode.getParent()) && Objects.equals(getName(), draftNode.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParent(), getName());
    }
    @JsonProperty("parent")
    public String parentName() {
        if (parent==null)
        {
            return null;
        }
        return parent.getName();
    }
}
