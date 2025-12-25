package raf.draft.dsw.controller.actions;

import com.sun.tools.javac.Main;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.NewProjectPanel;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.SingleTab;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTree;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;

public class CreateFileAction extends AbstractRoomAction{
    NewProjectPanel newProjectPanel;
    public CreateFileAction(NewProjectPanel newProjectPanel) {
        this.newProjectPanel=newProjectPanel;
    }
    public static boolean IsValidName(String paths[],String name)
    {
        for (String path:paths)
        {
            if (name.equals(path))
            {
                return false;
            }
           // System.out.println(name + path +"\n");
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        newProjectPanel.dispose();
        String paths[] = MainFrame.getInstance().getLastpath().toString().split("[ \\[,\\]]");
        if (IsValidName(paths,newProjectPanel.getTf3().getText())) {
            if (MainFrame.getInstance().getDraftTree().getSelectedNode() == null) {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR, "Nije izabran file u koji dodajete");
            } else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && newProjectPanel.getR1().isSelected() && MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof ProjectExplorer && !(newProjectPanel.getTf1().getText().isEmpty()) && !(newProjectPanel.getTf2().getText().isEmpty()) && !(newProjectPanel.getTf2().getText().isEmpty())) {
                boolean flag=true;
                for (DraftNode draftNode: ((DraftNodeComposite) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).children)
                {
                    if (newProjectPanel.getTf3().getText().equals(draftNode.getName()))
                    {
                        flag=false;
                    }
                }
                if (flag) {
                    MainFrame.getInstance().getDraftTree().addChild(MainFrame.getInstance().getDraftTree().getSelectedNode(), newProjectPanel.getTf1().getText(), newProjectPanel.getTf2().getText(), newProjectPanel.getTf3().getText());
                }
                else
                {
                    ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Vec postoji element sa tim imenom");
                }
            } else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project && newProjectPanel.getR2().isSelected() && !(newProjectPanel.getTf3().getText().isEmpty())) {
                boolean flag=true;
                for (DraftNode draftNode: ((DraftNodeComposite) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).children)
                {
                    if (newProjectPanel.getTf3().getText().equals(draftNode.getName()))
                    {
                        flag=false;
                    }
                }
                if (flag) {
                    MainFrame.getInstance().getDraftTree().addChild(MainFrame.getInstance().getDraftTree().getSelectedNode(), "", "", newProjectPanel.getTf3().getText());
                }
                else
                {
                    ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Vec postoji element sa tim imenom");
                }
            }
            else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project || MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Building) && newProjectPanel.getR3().isSelected() && !(newProjectPanel.getTf3().getText().isEmpty())) {
                boolean flag=true;
                for (DraftNode draftNode: ((DraftNodeComposite) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).children)
                {
                    if (newProjectPanel.getTf3().getText().equals(draftNode.getName()))
                    {
                        flag=false;
                    }
                }
                if (flag) {
                    DraftNode roomForTabs=MainFrame.getInstance().getDraftTree().addChild(MainFrame.getInstance().getDraftTree().getSelectedNode(), "", "", newProjectPanel.getTf3().getText());
                    if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Building) {
                        if (((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent()).isTabsOpenFlag()) {
                            SingleTab singleTab = new SingleTab();
                            singleTab.setRoom((Room) roomForTabs);
                            singleTab.setPath(((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent()).getName());
                            singleTab.setName(newProjectPanel.getTf3().getText());
                            singleTab.setBuilding((Building) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode());
                            singleTab.setProject((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode().getParent());
                            singleTab.setAutor(singleTab.getProject().getAutor());
                            singleTab.setRoomView(new RoomView(new BorderLayout()));
                            MainFrame.getInstance().getDraftTree().addSubsriber(singleTab.getRoomView());
                            MainFrame.getInstance().getTabs().getTabs().add(singleTab);
                            MainFrame.getInstance().getDraftTree().notifySubscribers();
                        }
                    }

                    if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project) {
                        if (((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).isTabsOpenFlag()) {
                            SingleTab singleTab = new SingleTab();
                            singleTab.setRoom((Room) roomForTabs);
                            singleTab.setPath(((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode()).getName());
                            singleTab.setName(newProjectPanel.getTf3().getText());
                            singleTab.setBuilding(null);
                            singleTab.setProject((Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode());
                            singleTab.setAutor(singleTab.getProject().getAutor());
                            singleTab.setRoomView(new RoomView(new BorderLayout()));
                            MainFrame.getInstance().getDraftTree().addSubsriber(singleTab.getRoomView());
                            MainFrame.getInstance().getTabs().getTabs().add(singleTab);
                            MainFrame.getInstance().getDraftTree().notifySubscribers();
                        }
                    }
                }
                else
                {
                    ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Vec postoji element sa tim imenom");
                }

            } else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && !(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof ProjectExplorer) && (newProjectPanel.getR1().isSelected())) {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR, "Projekat se moze napraviti samo u ProjectExploreru");
            } else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && !(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project) && (newProjectPanel.getR2().isSelected())) {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR, "Building se moze napraviti samo u Projectu");
            } else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && !(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project || MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Building) && (newProjectPanel.getR3().isSelected())) {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR, "Room se moze napraviti samo u Projectu ili Buildingu");
            } else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && (newProjectPanel.getR2().isSelected() || newProjectPanel.getR3().isSelected()) && newProjectPanel.getTf3().getText().isEmpty()) {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR, "Nije uneto ime fajla");
            } else if (MainFrame.getInstance().getDraftTree().getSelectedNode() != null && newProjectPanel.getR1().isSelected() && (newProjectPanel.getTf3().getText().isEmpty() || newProjectPanel.getTf1().getText().isEmpty() || newProjectPanel.getTf2().getText().isEmpty())) {
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR, "Nisu popunjena sva polja");
            }
        }
        else
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Vec postoji file sa tim nazivom u tom delu hijerarhije");
        }




    }
}
