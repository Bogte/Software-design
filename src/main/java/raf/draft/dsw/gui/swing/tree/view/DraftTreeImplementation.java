package raf.draft.dsw.gui.swing.tree.view;

import lombok.Getter;
import raf.draft.dsw.controller.states.AddState;
import raf.draft.dsw.controller.states.StateObserver.SISubscriber;
import raf.draft.dsw.controller.tabobserver.TubISubscriber;
import raf.draft.dsw.factories.NodeFactory;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class DraftTreeImplementation implements DraftTree {

    @Getter
    private DraftTreeView treeView;
    private DefaultTreeModel treeModel;
    private NodeFactory nodeFactory;
    private List<TubISubscriber> tubISubscribers;
    @Getter
    private RoomElement currElement;
    private List<SISubscriber> roomViews;

    @Override
    public DraftTreeView generateTree(ProjectExplorer projectExplorer) {
        DraftTreeItem root = new DraftTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new DraftTreeView(treeModel);
        nodeFactory = new NodeFactory();
        tubISubscribers=new ArrayList<>();
        roomViews=new ArrayList<>();
        return treeView;
    }

    @Override
    public DraftNode addChild(DraftTreeItem parent, String path, String autor, String name) {
        if (!(parent.getDraftNode() instanceof DraftNodeComposite))
            return null;

        DraftNode child = createChild(parent.getDraftNode(), name, autor, path);
        findProject(child);
        parent.add(new DraftTreeItem(child));
        ((DraftNodeComposite) parent.getDraftNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        informSubscribers();
        return child;

    }

    public void addChild2(DraftTreeItem parent,DraftNode child)
    {
        findProject(parent.getDraftNode());
        if (!(parent.getDraftNode() instanceof DraftNodeComposite))
            return;
        parent.add(new DraftTreeItem(child));
        ((DraftNodeComposite) parent.getDraftNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        informSubscribers();
    }

    @Override
    public void removeChildren(DraftTreeItem parent) {
        findProject(parent.getDraftNode());
        if (!(parent.getDraftNode() instanceof DraftNodeComposite)) {
            ((DraftNodeComposite) parent.getDraftNode().getParent()).getChildren().remove(parent.getDraftNode());
            ((DraftTreeItem) parent.getParent()).remove(parent);
        }
        else {
            ((DraftNodeComposite) parent.getDraftNode()).getChildren().removeAll(((DraftNodeComposite) parent.getDraftNode()).getChildren());
            ((DraftNodeComposite)parent.getDraftNode().getParent()).getChildren().remove(parent.getDraftNode());
            parent.removeAllChildren();
            ((DraftTreeItem) parent.getParent()).remove(parent);
        }
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        informSubscribers();
    }

    @Override
    public List<DraftTreeItem> getLeafNodes(DraftTreeItem node) {
        List<DraftTreeItem> leafNodes = new ArrayList<>();

        if (node.isLeaf()) {
            leafNodes.add(node);
        }
        else if(!node.isLeaf() && node.getDraftNode() instanceof Room) {
            leafNodes.add(node);
        }
        else {
            Enumeration<TreeNode> children = node.children();
            while (children.hasMoreElements()) {
                DraftTreeItem childNode = (DraftTreeItem) children.nextElement();
                leafNodes.addAll(getLeafNodes(childNode));
            }
        }

        return leafNodes;
    }

    @Override
    public DraftTreeItem getSelectedNode() {
        return (DraftTreeItem) treeView.getLastSelectedPathComponent();
    }
    @Override
    public void setSelectedNode(DraftNode draftNode) {
        DraftTreeItem root = (DraftTreeItem) treeModel.getRoot();
        DraftTreeItem selectedItem = findNode(root, draftNode);
        if (selectedItem != null) {
            TreePath path = new TreePath(selectedItem.getPath());
            treeView.setSelectionPath(path);
        }
    }

    private DraftTreeItem findNode(DraftTreeItem currentItem, DraftNode draftNode) {
        if (currentItem.getDraftNode().equals(draftNode)) {
            return currentItem;
        }
        for (int i = 0; i < currentItem.getChildCount(); i++) {
            DraftTreeItem childItem = (DraftTreeItem) currentItem.getChildAt(i);
            DraftTreeItem result = findNode(childItem, draftNode);
            if (result != null) {
                return result;
            }
        }

        return null;
    }

    private DraftNode createChild(DraftNode parent, String name, String autor, String path) {
        if (!(parent instanceof Room))
        {
            return nodeFactory.makenode(parent, name, autor, path);
        }
        String name1=((AddState)(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView().getStateManager().getCurrState())).getSelectedOption();
        int positionx=((AddState)(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView().getStateManager().getCurrState())).getLastClickPositionX();
        int positiony=((AddState)(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView().getStateManager().getCurrState())).getLastClickPositionY();
        int cmLenght=((AddState)(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView().getStateManager().getCurrState())).getCmWidth();
        int cmHeight=((AddState)(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView().getStateManager().getCurrState())).getCmheight();
        currElement= MainFrame.getInstance().getRoomElementFactory().makeElement(name1,positionx,positiony,cmLenght,cmHeight);
            return currElement;
    }
    @Override
    public void addSubsriber(TubISubscriber subscriber) {
        tubISubscribers.add(subscriber);
    }
    @Override
    public void removeSubscriber(TubISubscriber subscriber) {
        tubISubscribers.remove(subscriber);
    }
    @Override
    public void notifySubscribers() {
        for (TubISubscriber subscriber:tubISubscribers)
        {
            subscriber.refreshTabs();
        }
    }

    @Override
    public void addSubsriber(SISubscriber subscriber) {
        roomViews.add(subscriber);
    }

    @Override
    public void removeSubscriber(SISubscriber subscriber) {
        roomViews.remove(subscriber);
    }

    @Override
    public void informSubscribers() {
        for (SISubscriber subscriber:roomViews)
        {
            subscriber.refreshRoomViews();
        }
    }
    public void findProject(DraftNode draftNode)
    {
        while (!(draftNode instanceof Project))
        {
            draftNode=draftNode.getParent();
        }
        ((Project)draftNode).setChanged(true);
    }
}