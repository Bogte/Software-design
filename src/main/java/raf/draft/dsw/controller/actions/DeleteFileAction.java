package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.SingleTab;
import raf.draft.dsw.gui.swing.tab.Tab;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteFileAction extends AbstractRoomAction {
    public DeleteFileAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.SHIFT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/garbageicon.png"));
        putValue(NAME, "Delete file");
        putValue(SHORT_DESCRIPTION, "Delete file");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof ProjectExplorer)) {
           if ((MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project)) {
               List<SingleTab> singleTabs = new ArrayList<>();

               for (SingleTab singleTab1 : (MainFrame.getInstance().getTabs().getTabs())) {
                   if (singleTab1.getProject().equals(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode())) {
                       singleTabs.add(singleTab1);
                   }
               }
               MainFrame.getInstance().getTabs().getTabs().removeAll(singleTabs);
               MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
               MainFrame.getInstance().getDraftTree().notifySubscribers();
           }
           else if ((MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Building))
           {
               List<SingleTab> singleTabs = new ArrayList<>();
               for (SingleTab singleTab1 : (MainFrame.getInstance().getTabs().getTabs())) {
                   if (singleTab1.getBuilding() == null){continue;}
                   if (singleTab1.getBuilding().equals(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode())) {
                       singleTabs.add(singleTab1);
                   }
               }
               MainFrame.getInstance().getTabs().getTabs().removeAll(singleTabs);
               MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
               MainFrame.getInstance().getDraftTree().notifySubscribers();
           }
           else if((MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Room))
           {
               List<SingleTab> singleTabs = new ArrayList<>();
               for (SingleTab singleTab1 : (MainFrame.getInstance().getTabs().getTabs())) {
                   boolean flag=false;
                   if (singleTab1.getName().equals(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getName()))
                   {
                       if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent() instanceof Building)
                       {
                           if (singleTab1.getProject().equals(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent().getParent()))
                           {
                               flag=true;
                           }
                       }
                       else if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent() instanceof Project)
                       {
                           if (singleTab1.getProject().equals(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent()))
                           {
                               flag=true;
                           }
                       }
                       if (flag) {
                           if (singleTab1.getBuilding() != null && MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getBuilding() != null) {
                               if (singleTab1.getBuilding().equals(((Room) (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode())).getBuilding())) {
                                   singleTabs.add(singleTab1);
                               }
                           } else if (singleTab1.getBuilding() == null && MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getBuilding() == null) {
                                   singleTabs.add(singleTab1);
                           }
                       }
                   }
               }
               MainFrame.getInstance().getTabs().getTabs().removeAll(singleTabs);
               MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
               MainFrame.getInstance().getDraftTree().notifySubscribers();

           }

        }
        else
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"ProjectExplorer ne moze biti obrisan");
        }
    }
}
