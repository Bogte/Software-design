package raf.draft.dsw.controller.commands.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.nodes.RoomElement;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class MoveCommand extends AbstractCommand {
    private List<BasicPainter> basicPainters;
    private List<Integer> positionXstart;
    private List<Integer> positionXend;
    private List<Integer> positionYstart;
    private List<Integer> positionYend;
    private boolean first=true;
    private RoomView roomView;

    public MoveCommand(RoomView roomView) {
        basicPainters=new ArrayList<>();
        positionXstart=new ArrayList<>();
        positionXend=new ArrayList<>();
        positionYstart=new ArrayList<>();
        positionYend=new ArrayList<>();
        this.roomView=roomView;
    }
    @Override
    public void doCommand() {
        for (int i=0; i<basicPainters.size();i++)
        {
            basicPainters.get(i).getRoomElement().setLocationStartX(positionXend.get(i));
            basicPainters.get(i).getRoomElement().setLocationStartY(positionYend.get(i));
        }
       roomView.repaint();

    }

    @Override
    public void undoCommand() {
        for (int i=0; i<basicPainters.size();i++)
        {

            basicPainters.get(i).getRoomElement().setLocationStartX(positionXstart.get(i));
            basicPainters.get(i).getRoomElement().setLocationStartY(positionYstart.get(i));
        }
        roomView.repaint();
    }
}
