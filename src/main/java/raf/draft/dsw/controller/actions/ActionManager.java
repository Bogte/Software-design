package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.states.statesActions.RotateLeftAction;
import raf.draft.dsw.controller.states.statesActions.RotateRightAction;
import raf.draft.dsw.controller.states.statesActions.SEditRoomAction;
import raf.draft.dsw.gui.swing.ChangeFileInfoPanel;

public class ActionManager {
    private ExitAction exitAction = new ExitAction();
    private AboutUs aboutUs = new AboutUs();
    private NewProjectAction newProjectAction = new NewProjectAction();
    private ChangeFileInfoAction changeFileInfoAction=new ChangeFileInfoAction();
    private RedoAction redoAction=new RedoAction();
    private UndoAction undoAction=new UndoAction();
    private SaveAction saveAction= new SaveAction();
    private OpenFileAction openFileAction=new OpenFileAction();
    private SaveAsAction saveAsAction=new SaveAsAction();

    public ExitAction getExitAction() {
        return exitAction;
    }

    public AboutUs getAboutUs() {return aboutUs;}

    public NewProjectAction getNewProjectAction() {return newProjectAction;}
    public DeleteFileAction getDeleteFileAction(){return new DeleteFileAction();}
    public ChangeFileInfoAction getFileInfoAction(){return changeFileInfoAction;};
    public RedoAction getRedoAction(){return redoAction;};
    public UndoAction getUndoAction(){return undoAction;};
    public SaveAction getSaveAction(){return saveAction;};
    public OpenFileAction getOpenFileAction(){return openFileAction;};
    public SaveAsAction getSaveAsAction(){return saveAsAction;};


}
