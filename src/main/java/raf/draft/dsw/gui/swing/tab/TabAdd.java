package raf.draft.dsw.gui.swing.tab;

import raf.draft.dsw.controller.tabobserver.TubISubscriber;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;

import java.util.List;

public interface TabAdd extends TubISubscriber {
    void addTab(List<DraftTreeItem> leafNodes, String projectName);
}
