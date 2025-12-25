package raf.draft.dsw.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.ChangeFileInfoPanel;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.SingleTab;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ChangingFileInfoAction extends AbstractRoomAction{
    private ChangeFileInfoPanel changeFileInfoPanel;

    public ChangingFileInfoAction(ChangeFileInfoPanel changeFileInfoPanel) {
        this.changeFileInfoPanel = changeFileInfoPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        changeFileInfoPanel.dispose();
        if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project)
        {
            if (!(changeFileInfoPanel.getTf1().getText().equals("") && changeFileInfoPanel.getTf2().getText().equals("") && changeFileInfoPanel.getTf3().getText().equals("")))
            {   boolean flag=true;
                for (DraftNode project :((ProjectExplorer) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent()).getChildren())
                {
                    if (project.getName().equals(changeFileInfoPanel.getTf3().getText()))
                    {
                        flag=false;
                    }
                }
                if (flag) {
                    MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().setName(changeFileInfoPanel.getTf3().getText());
                    ((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).setAutor(changeFileInfoPanel.getTf1().getText());
                    ((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).setFile(changeFileInfoPanel.getTf2().getText());
                    MainFrame.getInstance().getDraftTree().notifySubscribers();
                }
                else
                {
                    ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Vec postoji projekat sa tim imenom");
                }
            }
            else
            {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Nisu popunjena sva polja");
            }

        }
        else {
            if (!(changeFileInfoPanel.getTf3().getText().equals(""))) {
                boolean flag=true;
                for (DraftNode project :((DraftNodeComposite) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent()).getChildren())
                {
                    if (project.getName().equals(changeFileInfoPanel.getTf3().getText())) {
                        flag=false;
                    }
                }
                if (flag) {
                    if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Room) {
                        for (SingleTab singleTab : MainFrame.getInstance().getTabs().getTabs()) {
                            if (singleTab.getName().equals(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getName())) {
                                singleTab.setName(changeFileInfoPanel.getTf3().getText());
                            }

                        }
                    }
                    MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().setName(changeFileInfoPanel.getTf3().getText());
                    MainFrame.getInstance().getDraftTree().notifySubscribers();
                }
                else
                {
                    ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Vec postoji file sa tim imenom");
                }
            }
            else
            {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Nije uneto novo ime");
            }
        }

    }
}
