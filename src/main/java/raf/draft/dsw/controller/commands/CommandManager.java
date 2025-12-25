package raf.draft.dsw.controller.commands;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
//        currentCommand++;
//        if (currentCommand!=0)
//        {
//            MainFrame.getInstance().getToolBar().getUndoAction().setEnabled(true);
//        }
    }
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            MainFrame.getInstance().getToolBar().getUndoAction().setEnabled(true);
        }
        if(currentCommand==commands.size()){
            MainFrame.getInstance().getToolBar().getRedoAction().setEnabled(false);
        }
    }
    public void undoCommand(){
        if(currentCommand > 0){
           MainFrame.getInstance().getToolBar().getRedoAction().setEnabled(true);
            commands.get(--currentCommand).undoCommand();

        }
        if(currentCommand==0){
            MainFrame.getInstance().getToolBar().getUndoAction().setEnabled(false);
        }
    }
}
