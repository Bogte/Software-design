package raf.draft.dsw.factories;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.elements.*;

public class RoomElementFactory {
    public RoomElement makeElement(String elType,int locationX,int locationY,int cmLenght,int cmHeight)
    {
        DraftNode parent=(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom());
        if (elType.equalsIgnoreCase("bathtub"))
        {
            return  new Bathtub(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("bed"))
        {
            return  new Bed(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("boiler"))
        {
            return  new Boiler(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("closet"))
        {
            return  new Closet(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("door"))
        {
            return  new Door(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("sink"))
        {
            return  new Sink(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("table"))
        {
            return  new Table(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("toilet"))
        {
            return  new Toilet(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        else if (elType.equalsIgnoreCase("washingmachine"))
        {
            return  new WashingMachine(parent,locationX,locationY,cmLenght,cmHeight,0);
        }
        return null;
        
    }
}
