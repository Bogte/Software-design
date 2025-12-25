package raf.draft.dsw.gui.swing.tree.view;

import raf.draft.dsw.controller.states.StateObserver.SIPublisher;
import raf.draft.dsw.controller.states.StateObserver.SISubscriber;
import raf.draft.dsw.controller.tabobserver.TubIPublisher;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.ProjectExplorer;

import java.util.List;

public interface DraftTree extends TubIPublisher, SIPublisher {

    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    DraftNode addChild(DraftTreeItem parent,String path,String autor,String name);
    void addChild2(DraftTreeItem parent,DraftNode child);
    void removeChildren(DraftTreeItem parent);
    DraftTreeItem getSelectedNode();
    List<DraftTreeItem> getLeafNodes(DraftTreeItem node);
    void setSelectedNode(DraftNode draftNode);
}
