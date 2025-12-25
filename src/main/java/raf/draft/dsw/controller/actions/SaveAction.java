package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.controller.serializer.Serializer;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.repository.DraftExplorerImplementation;
import raf.draft.dsw.model.repository.DraftRepository;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAction extends AbstractRoomAction{
    private Serializer serializer;

    public SaveAction() {
        putValue(NAME, "Save Explorer");
        putValue(SMALL_ICON, loadIcon("/images/SaveExplorer.png"));
        this.serializer = new Serializer();
        putValue(SHORT_DESCRIPTION, "Save explorer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        DraftTreeItem selectedItem = MainFrame.getInstance().getDraftTree().getSelectedNode();

       Project selectedProject = null;
//        if (MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView() != null) {
//            System.out.println("1");
//            Room focusedRoom = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom();
//            if (focusedRoom.getParent() instanceof Project) selectedProject = (Project) focusedRoom.getParent();
//            if (focusedRoom.getParent().getParent() instanceof Project)
//                selectedProject = (Project) focusedRoom.getParent().getParent();
//        }
//        else
//        {
        try {
            selectedProject = (Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode();
        }
        catch (ClassCastException r)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Izaberite projekat za cuvanje");
        }
//        }
        if (selectedItem.getDraftNode() instanceof Project) {
            selectedProject = (Project) selectedItem.getDraftNode();
        }

        if (selectedProject == null) {
            //ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR, "No project selected.");
            return;
        }
        if (!selectedProject.isChanged()) return;

        String filePath = selectedProject.getFile();
        File file = new File(filePath);
        if (!file.exists() || file.getName().equalsIgnoreCase("")) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("DraftRoom files (*.txt)", "txt");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(selectedProject.getName() + ".txt"));
            fileChooser.setSelectedFile(new File(selectedProject.getName() + ".txt"));
            fileChooser.setDialogTitle("Save Project As");
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File newFile = fileChooser.getSelectedFile();
                filePath = newFile.getAbsolutePath();
                if (!filePath.endsWith(serializer.getCustomExtension())) {
                    filePath = filePath + serializer.getCustomExtension();
                }
                selectedProject.setFile(filePath);
            } else {
                return;
            }

        }
         selectedProject.setChanged(false);
        try {
            serializer.save(selectedProject, new File(filePath));
            JOptionPane.showMessageDialog(null, "Project saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error saving project: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
