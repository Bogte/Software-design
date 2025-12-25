package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.serializer.Serializer;
import raf.draft.dsw.controller.states.MoveState;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.factories.PainterFactory;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.SingleTab;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeImplementation;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.repository.DraftRepository;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OpenFileAction extends AbstractRoomAction {
    private Serializer serializer;
    private Integer broj=0;

    public OpenFileAction() {
        putValue(NAME, "Upload Explorer");
        putValue(SMALL_ICON, loadIcon("/images/UploadExplorer.png"));
        putValue(SHORT_DESCRIPTION, "Upload explorer");
        this.serializer = new Serializer();
        broj=0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open Project");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            broj=0;
            try {
                Project loadedProject = serializer.load(selectedFile, Project.class);

                ProjectExplorer projectExplorer = ApplicationFramework.getInstance().getDraftRepository().getProjectExplorer();
                MainFrame.getInstance().getDraftTree().setSelectedNode(projectExplorer);

                addProjectAndChildrenToTree(projectExplorer, loadedProject);


                JOptionPane.showMessageDialog(null, "Project loaded successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addProjectAndChildrenToTree(ProjectExplorer projectExplorer, Project project) {
       // MainFrame.getInstance().getDraftTree().setSelectedNode(projectExplorer);
            MainFrame.getInstance().getDraftTree().addChild(MainFrame.getInstance().getDraftTree().getSelectedNode(), project.getFile(), project.getAutor(), project.getName());
        if (project.getChildren() != null && !project.getChildren().isEmpty()) {
            for (DraftNode childNode : project.getChildren()) {
                if (childNode instanceof DraftNodeComposite) {
                    if (childNode instanceof Project) {
                        MainFrame.getInstance().getDraftTree().setSelectedNode(childNode.getParent());
                        MainFrame.getInstance().getDraftTree().addChild2(MainFrame.getInstance().getDraftTree().getSelectedNode(),childNode);
                        addProjectAndChildrenToTree(projectExplorer, (Project) childNode);
                    } else if (childNode instanceof Room) {
                        MainFrame.getInstance().getDraftTree().setSelectedNode(project);
                        addRoomAndElementsToTree((Room) childNode);
                    }
                    else if (childNode instanceof Building)
                    {
                        MainFrame.getInstance().getDraftTree().setSelectedNode(project);
                        MainFrame.getInstance().getDraftTree().addChild2(MainFrame.getInstance().getDraftTree().getSelectedNode(),childNode);
                        childNode.setParent(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode());
                        for (DraftNode room:((Building) childNode).getChildren())
                        {
                            MainFrame.getInstance().getDraftTree().setSelectedNode(childNode);
                            addRoomAndElementsToTree((Room) room);
                        }

                    }
                }
            }
        }
    }

    private void addRoomAndElementsToTree(Room room) {
       // MainFrame.getInstance().getDraftTree().setSelectedNode(room.getParent());
        MainFrame.getInstance().getDraftTree().addChild2(MainFrame.getInstance().getDraftTree().getSelectedNode(),room);
        room.setParent(MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode());
        if (room.getChildren() != null && !room.getChildren().isEmpty()) {
            for (DraftNode childNode : room.getChildren()) {
                if (childNode instanceof RoomElement) {
                    DraftTreeItem elementItem = new DraftTreeItem(childNode);
                    MainFrame.getInstance().getDraftTree().setSelectedNode(childNode.getParent());
                    MainFrame.getInstance().getDraftTree().addChild2(MainFrame.getInstance().getDraftTree().getSelectedNode(), elementItem.getDraftNode());
                    getSpecificElement((RoomElement)childNode);
                }
            }
        }
    }
    public static void getSpecificElement(RoomElement roomElement) {
        if (roomElement instanceof Bathtub) {
            Bathtub.setBathtubNumber(Bathtub.getBathtubNumber()+1);
        }
        if (roomElement instanceof Sink) {
            Sink.setSinkNumber(Sink.getSinkNumber()+1);
        }
        if (roomElement instanceof Table) {
            Table.setTableNumber(Table.getTableNumber()+1);
        }
        if (roomElement instanceof WashingMachine) {
            WashingMachine.setWashingMachineNumber(WashingMachine.getWashingMachineNumber()+1);
        }
        if (roomElement instanceof Bed) {
            Bed.setBedNumber(Bed.getBedNumber()+1);
        }
        if (roomElement instanceof Boiler) {
            Boiler.setBoilerNumber(Boiler.getBoilerNumber()+1);
        }
        if (roomElement instanceof Closet) {
            Closet.setClosetNumber(Closet.getClosetNumber()+1);
        }
        if (roomElement instanceof Door) {
            Door.setDoorNumber(Door.getDoorNumber()+1);
        }
        if (roomElement instanceof Toilet) {
            Toilet.setToiletNumber(Toilet.getToiletNumber()+1);
        }

    }

}
