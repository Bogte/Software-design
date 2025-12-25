package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class DraftTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MainFrame.getInstance().setLastpath(e.getPath().toString());
        DraftTreeItem treeItemSelected = (DraftTreeItem) path.getLastPathComponent();
        System.out.println("Selektovan cvor:"+ treeItemSelected.getDraftNode().getName());
        System.out.println("getPath: "+e.getPath());
    }
}
