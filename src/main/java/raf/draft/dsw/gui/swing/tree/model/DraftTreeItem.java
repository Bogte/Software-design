package raf.draft.dsw.gui.swing.tree.model;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Objects;

@Getter
@Setter
public class DraftTreeItem extends DefaultMutableTreeNode {
    private DraftNode draftNode;

    public DraftTreeItem(DraftNode nodeModel) {
        this.draftNode = nodeModel;
    }

    @Override
    public String toString() {
        return draftNode.getName();
    }

    public void setName(String name) {
        this.draftNode.setName(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DraftTreeItem that = (DraftTreeItem) obj;
        return Objects.equals(this.draftNode.getName(), that.draftNode.getName()) &&
                Objects.equals(this.draftNode.getBuilding(), that.draftNode.getBuilding()) &&
                Objects.equals(this.draftNode.getParent(), that.draftNode.getParent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(draftNode.getName(),draftNode.getParent());
    }


}
