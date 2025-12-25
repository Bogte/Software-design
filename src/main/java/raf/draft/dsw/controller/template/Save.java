package raf.draft.dsw.controller.template;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.controller.actions.AbstractRoomAction;
import raf.draft.dsw.controller.serializer.Serializer;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tab.SingleTab;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Save extends AbstractRoomAction {

    private Serializer serializer;

    public Save(){
        putValue(SMALL_ICON, loadIcon("/images/Save.png"));
        putValue(SHORT_DESCRIPTION, "Save template");
        serializer = new Serializer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Room focusedRoom = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom();

        DraftNodeComposite selectedProject = (DraftNodeComposite) focusedRoom.getParent();
        while (!(selectedProject instanceof Project)) {
            selectedProject = (DraftNodeComposite) focusedRoom.getParent();
        }

        Project project = (Project) selectedProject;

        if (!project.isChanged()) return;
        String filePath = project.getFile();

        File file = new File(filePath);
        if (!file.exists() || file.getName().equalsIgnoreCase("")) {
            JFileChooser fileChooser = new JFileChooser(new File("src/main/resources/JSON files"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Room template files (*.json)", "json");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(selectedProject.getName()));
            //fileChooser.setSelectedFile(new File(selectedProject.getName() + ".json"));
            fileChooser.setDialogTitle("Save Project As");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File newFile = fileChooser.getSelectedFile();
                filePath = newFile.getAbsolutePath();
//                if (!filePath.endsWith(serializer.getCustomExtension())) {
//                    filePath = filePath + serializer.getCustomExtension();
//                }
                project.setFile(filePath);
            } else {
                return;
            }

        }
        project.setChanged(false);

        try {
            serializer.saveTemplate(focusedRoom, new File(filePath));
            System.out.println("JSON file created: roomView.json");

            JOptionPane.showMessageDialog(null, "Project saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error saving project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
