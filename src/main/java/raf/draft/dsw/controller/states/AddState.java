package raf.draft.dsw.controller.states;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.draft.dsw.controller.commands.AbstractCommand;
import raf.draft.dsw.controller.commands.implementation.AddCommand;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.controller.states.statesActions.MoveAction;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.factories.PainterFactory;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Locale;

@NoArgsConstructor
public class AddState implements State{
    @Getter
    @Setter
    private int lastClickPositionX;
    @Getter
    @Setter
    private int lastClickPositionY;
    @Getter
    private int cmWidth;
    @Getter
    private int cmheight;
    @Getter
    private String selectedOption;
    @Getter
    private PainterFactory painterFactory=new PainterFactory();
    private boolean add=false;
    @Override
    public void run(RoomView roomView,MouseEvent e) {
        try {
            String[] options = {"bathtub", "bed", "boiler", "closet", "door", "sink", "table", "toilet", "washingmachine"};
            selectedOption = (String) JOptionPane.showInputDialog(null, "Izaberite opciju:", "Izbor predmeta", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            String width = JOptionPane.showInputDialog(roomView, "Unesite širinu sobe (u cm):");
            String height = JOptionPane.showInputDialog(roomView, "Unesite visinu sobe (u cm):");
            cmWidth = (int) (Integer.parseInt(width) * roomView.getAspectRatio());
            cmheight = (int) (Integer.parseInt(height) * roomView.getAspectRatio());
            MainFrame.getInstance().getDraftTree().setSelectedNode(null);
            MainFrame.getInstance().getDraftTree().setSelectedNode(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom());
            DraftTreeItem parent = MainFrame.getInstance().getDraftTree().getSelectedNode();
            DraftNode roomel = MainFrame.getInstance().getDraftTree().addChild(parent, "", "", selectedOption);
            MainFrame.getInstance().getDraftTree().setSelectedNode(roomel);
            MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
            Painter painter = painterFactory.PainterFactory(((RoomElement) roomel));
            if (!isItCut(roomView,painter))
            {
//                roomView.getPainters().add(painter);
                AbstractCommand command = new AddCommand((BasicPainter)painter);
                ApplicationFramework.getInstance().getCommandManager().addCommand(command);
            }
            else
            {
                MainFrame.getInstance().getDraftTree().setSelectedNode(roomel);
                getSpecificElement((RoomElement)roomel);
                MainFrame.getInstance().getDraftTree().removeChildren(MainFrame.getInstance().getDraftTree().getSelectedNode());
                ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Vec postoji element na tom mestu ili se elementi presecaju");

            }

        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Nisu uneti svi podaci");
        }


    }

    @Override
    public void myMouseClick(MouseEvent e) {
        run((MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex())).getRoomView(),e);
    }

    @Override
    public void myMouseDrag(MouseEvent e) {}

    @Override
    public void myMousePressed(MouseEvent e) {}

    @Override
    public void myKeyPressed(KeyEvent e) {}

    @Override
    public void myMouseWheelMoved(MouseWheelEvent e) {}

    @Override
    public void MyMouseRelease(MouseEvent e) {}
    private boolean isItCut(RoomView roomView, Painter newPainter) {
        for (Painter painter : roomView.getPainters()) {
            if (newPainter != painter) {
                BasicPainter basicPainter = (BasicPainter) painter;
                int x1 = basicPainter.getRoomElement().getLocationX();
                int y1 = basicPainter.getRoomElement().getLocationY();
                int width1 = basicPainter.getRoomElement().getCmLenght();
                int height1 = basicPainter.getRoomElement().getCmHeight();

                int x2 = ((BasicPainter) newPainter).getRoomElement().getLocationX();
                int y2 = ((BasicPainter) newPainter).getRoomElement().getLocationY();
                int width2 = ((BasicPainter) newPainter).getRoomElement().getCmLenght();
                int height2 = ((BasicPainter) newPainter).getRoomElement().getCmHeight();
                if (x1 < x2 + width2 && x1 + width1 > x2 &&
                        y1 < y2 + height2 && y1 + height1 > y2) {
                    return true;
                }
            }
        }
        return false;
    }
    public static void getSpecificElement(RoomElement roomElement) {
        if (roomElement instanceof Bathtub) {
            Bathtub.setBathtubNumber(Bathtub.getBathtubNumber()-1);
        }
        if (roomElement instanceof Sink) {
            Sink.setSinkNumber(Sink.getSinkNumber()-1);
        }
        if (roomElement instanceof Table) {
            Table.setTableNumber(Table.getTableNumber()-1);
        }
        if (roomElement instanceof WashingMachine) {
            WashingMachine.setWashingMachineNumber(WashingMachine.getWashingMachineNumber()-1);
        }
        if (roomElement instanceof Bed) {
            Bed.setBedNumber(Bed.getBedNumber()-1);
        }
        if (roomElement instanceof Boiler) {
            Boiler.setBoilerNumber(Boiler.getBoilerNumber()-1);
        }
        if (roomElement instanceof Closet) {
            Closet.setClosetNumber(Closet.getClosetNumber()-1);
        }
        if (roomElement instanceof Door) {
            Door.setDoorNumber(Door.getDoorNumber()-1);
        }
        if (roomElement instanceof Toilet) {
            Toilet.setToiletNumber(Toilet.getToiletNumber()-1);
        }

    }


}
