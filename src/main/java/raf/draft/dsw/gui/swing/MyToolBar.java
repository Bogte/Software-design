package raf.draft.dsw.gui.swing;

import lombok.Getter;
import raf.draft.dsw.controller.actions.*;
import raf.draft.dsw.controller.serializer.Serializer;
import raf.draft.dsw.core.ApplicationFramework;

import javax.swing.*;
@Getter
public class MyToolBar extends JToolBar {
    private RedoAction redoAction;
    private UndoAction undoAction;
    public MyToolBar(ActionManager actionManager){
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction ea = actionManager.getExitAction();
        add(ea);

        AboutUs au = actionManager.getAboutUs();
        add(au);

        NewProjectAction npa = actionManager.getNewProjectAction();
        add(npa);

        DeleteFileAction dfa=actionManager.getDeleteFileAction();
        add(dfa);
        ChangeFileInfoAction cfia=actionManager.getFileInfoAction();
        add(cfia);
        undoAction=actionManager.getUndoAction();
        //undoAction.setEnabled(false);
        add(undoAction);
        redoAction=actionManager.getRedoAction();
        //redoAction.setEnabled(false);
        add(redoAction);
        SaveAction saveAction=actionManager.getSaveAction();
        add(saveAction);
        OpenFileAction openFileAction=actionManager.getOpenFileAction();
        add(openFileAction);
        SaveAsAction saveAsAction=actionManager.getSaveAsAction();
        add(saveAsAction);

    }
}
