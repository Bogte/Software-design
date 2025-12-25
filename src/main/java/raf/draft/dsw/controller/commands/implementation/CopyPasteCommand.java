package raf.draft.dsw.controller.commands.implementation;

import lombok.Getter;
import raf.draft.dsw.controller.commands.AbstractCommand;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.factories.PainterFactory;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.RoomElement;

import java.util.ArrayList;
import java.util.List;

public class CopyPasteCommand extends AbstractCommand {
    @Getter
    private PainterFactory painterFactory=new PainterFactory();
    private List<Painter> painters=new ArrayList<>();
    private final int offset=20;
    private boolean first=false;
    @Override
    public void doCommand() {
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.copySelectedItems();
        for (Prototype p : roomView.getItems()) {
            Painter painter = painterFactory.PainterFactory(((RoomElement) p));
            roomView.getPainters().add(painter);
            painters.add(painter);
        }
//        roomView.getItems().clear();
        if (first) {
            for (Prototype p : roomView.getItems()) {
                ((RoomElement) p).setLocationX(((RoomElement) p).getLocationX() - offset);
                ((RoomElement) p).setLocationY(((RoomElement) p).getLocationY() - offset);
            }
        }
        first=true;
        roomView.repaint();
    }

    @Override
    public void undoCommand() {
        RoomView roomView = MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.getPainters().removeAll(painters);
        roomView.repaint();
    }
}
