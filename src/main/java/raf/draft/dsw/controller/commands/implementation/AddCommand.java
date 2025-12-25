package raf.draft.dsw.controller.commands.implementation;

import raf.draft.dsw.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class AddCommand extends AbstractCommand {
    private BasicPainter basicPainter;

    public AddCommand(BasicPainter basicPainter) {
        this.basicPainter=basicPainter;
    }

    @Override
    public void doCommand() {
        if(basicPainter==null) return;
        RoomView roomView= MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.getPainters().add(basicPainter);
           MainFrame.getInstance().getDraftTree().setSelectedNode(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom());
           MainFrame.getInstance().getDraftTree().addChild2(MainFrame.getInstance().getDraftTree().getSelectedNode(),((DraftNode)basicPainter.getRoomElement()));
          roomView.repaint();

    }

    @Override
    public void undoCommand() {
        if(basicPainter==null) return;
        RoomView roomView= MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.getPainters().remove(basicPainter);
        MainFrame.getInstance().getDraftTree().setSelectedNode(basicPainter.getRoomElement());
        MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
        roomView.repaint();
    }
}
