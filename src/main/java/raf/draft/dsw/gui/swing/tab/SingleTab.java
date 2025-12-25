package raf.draft.dsw.gui.swing.tab;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTree;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import java.util.Objects;

@Getter
@Setter
public class SingleTab {
    private String name;
    private String autor;
    private String path;
    private Project Project;
    private Building building;
    private RoomView roomView;
    private Room room;

    public SingleTab() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleTab singleTab = (SingleTab) o;
        return Objects.equals(name, singleTab.name) && Objects.equals(Project, singleTab.Project) && Objects.equals(building, singleTab.building);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Project, building);
    }
}
