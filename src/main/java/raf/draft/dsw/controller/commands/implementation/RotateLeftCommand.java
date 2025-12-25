package raf.draft.dsw.controller.commands.implementation;

import raf.draft.dsw.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;

import java.util.List;

public class RotateLeftCommand extends AbstractCommand {
    private List<Painter>painters;

    public RotateLeftCommand(List<Painter> painters) {
        this.painters = painters;
    }

    @Override
    public void doCommand() {
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        for (Painter p : roomView.getSelectedPainters()) {
            if (p instanceof BasicPainter) {
                ((BasicPainter) p).rotate(-90);
            }
        }
        roomView.repaint();
    }

    @Override
    public void undoCommand() {
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        for (Painter p : painters) {
            if (p instanceof BasicPainter) {
                ((BasicPainter) p).rotate(90);
            }
        }
        roomView.repaint();
    }
}
