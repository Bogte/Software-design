package raf.draft.dsw.controller.template;

import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.serializer.Serializer;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.factories.PainterFactory;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class Upload extends AbstractRoomAction {

    private Serializer serializer;
    int broj;

    public Upload() {
        putValue(SMALL_ICON, loadIcon("/images/Upload.png"));
        putValue(SHORT_DESCRIPTION, "Upload template");
        serializer = new Serializer();
        broj = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser(new File("src/main/resources/JSON files"));
        fileChooser.setDialogTitle("Open Project");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            broj = 0;
            try {
                Room room = serializer.loadJSON(selectedFile);
                Room newRoom = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom();

                RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
                roomView.setRoomDimensions(room.getCmLenght(), room.getCmHeight());
                roomView.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                PainterFactory pf = new PainterFactory();
                for(DraftNode draftNode : room.getChildren()){
                    Painter painter = pf.PainterFactory((RoomElement) draftNode);
                    roomView.getPainters().add(painter);
                    MainFrame.getInstance().getDraftTree().setSelectedNode(newRoom);
                    MainFrame.getInstance().getDraftTree().addChild2(MainFrame.getInstance().getDraftTree().getSelectedNode(), draftNode);
                }

                JOptionPane.showMessageDialog(null, "Project loaded successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error loading project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
