package raf.draft.dsw.factories;

import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.painters.implementationPainters.*;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.structures.elements.*;

public class PainterFactory {
    public Painter PainterFactory(RoomElement roomElement) {
        if (roomElement instanceof Bathtub)
        {
            return  new BathtubPainter(roomElement);
        }
        else if (roomElement instanceof Bed)
        {
            return  new BedPainter(roomElement);
        }
        else if (roomElement instanceof Boiler)
        {
            return  new BoilerPainter(roomElement);
        }
        else if (roomElement instanceof Closet)
        {
            return  new ClosetPainter(roomElement);
        }
        else if (roomElement instanceof Door)
        {
            return  new DoorPainter(roomElement);
        }
        else if (roomElement instanceof Sink)
        {
            return  new SinkPainter(roomElement);
        }
        else if (roomElement instanceof Table)
        {
            return  new TablePainter(roomElement);
        }
        else if (roomElement instanceof Toilet)
        {
            return  new ToiletPainter(roomElement);
        }
        else if (roomElement instanceof WashingMachine)
        {
            return  new WashingMachinePainter(roomElement);
        }
        return null;

    }

}
