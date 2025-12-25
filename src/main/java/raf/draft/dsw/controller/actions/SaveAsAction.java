package raf.draft.dsw.controller.actions;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.structures.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class SaveAsAction extends AbstractRoomAction {
    public SaveAsAction() {
        putValue(NAME, "Save As");
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(SHORT_DESCRIPTION, "Save as");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Project project = (Project) MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode();
        String currentFilePath = project.getFile();
        if (currentFilePath == null || currentFilePath.isEmpty()) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Nema sačuvanog fajla.");
            return;
        }
        String newName = JOptionPane.showInputDialog("Unesite novo ime za fajl (ili pritisnite Cancel da biste sačuvali sa starim imenom)");
        if (newName == null || newName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Ime nije promenjeno.");
            return;
        }
        if (!newName.endsWith(".txt")) {
            newName = newName + ".txt";
        }
        File currentFile = new File(currentFilePath);
        File newFile = new File(currentFile.getParent(), newName);
        try {
            if (newFile.exists()) {
                newFile.delete();
            }
            if (currentFile.exists()) {
                boolean isCopied = currentFile.renameTo(newFile);
                if (!isCopied) {
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), "Greška pri preimenovanju fajla.");
                    return;
                }
            }
            project.setFile(newFile.getAbsolutePath());
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Fajl je sačuvan kao: " + newFile.getAbsolutePath());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "Došlo je do greške pri preimenovanju fajla: " + ex.getMessage());
        }
    }
}
