package raf.draft.dsw.controller.commands.implementation;

import raf.draft.dsw.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.DraftNode;

import java.util.List;

public class DeleteCommand extends AbstractCommand {
    private List<BasicPainter> basicPainters;

    public DeleteCommand(List<BasicPainter> basicPainters) {
        this.basicPainters = basicPainters;
    }

    @Override
    public void doCommand() {
        if(basicPainters.isEmpty()) return;
        RoomView roomView= MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
        roomView.getPainters().removeAll(basicPainters);
        for (BasicPainter bp: basicPainters)
        {
            MainFrame.getInstance().getDraftTree().setSelectedNode(bp.getRoomElement());
            MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
        }
        roomView.repaint();
    }

    @Override
    public void undoCommand() {
        if(basicPainters.isEmpty()) return;
        RoomView roomView= MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView();
       for (BasicPainter bp: basicPainters)
       {
           MainFrame.getInstance().getDraftTree().setSelectedNode(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom());
           MainFrame.getInstance().getDraftTree().addChild2(MainFrame.getInstance().getDraftTree().getSelectedNode(),((DraftNode)bp.getRoomElement()));
        }
        roomView.getPainters().addAll(basicPainters);
        roomView.repaint();
    }
}
