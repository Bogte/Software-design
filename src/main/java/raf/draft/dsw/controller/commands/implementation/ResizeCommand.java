package raf.draft.dsw.controller.commands.implementation;

import raf.draft.dsw.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.RoomElement;

public class ResizeCommand extends AbstractCommand {
    private int oldWidht;
    private int oldHeight;
    private int newWidth;
    private int newHeight;
    private RoomElement roomElement;

    public ResizeCommand(int oldWidht, int oldHeight, int newWidth, int newHeight, RoomElement roomElement) {
        this.oldWidht = oldWidht;
        this.oldHeight = oldHeight;
        this.newWidth = newWidth;
        this.newHeight = newHeight;
        this.roomElement = roomElement;
    }

    @Override
    public void doCommand() {
        roomElement.setCmLenght(newWidth);
        roomElement.setCmHeight(newHeight);
        RoomView roomView= MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.repaint();
    }

    @Override
    public void undoCommand() {
        roomElement.setCmLenght(oldWidht);
        roomElement.setCmHeight(oldHeight);
        RoomView roomView= MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.repaint();
    }
}
