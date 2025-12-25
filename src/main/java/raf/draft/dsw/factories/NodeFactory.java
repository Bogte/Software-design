package raf.draft.dsw.factories;

import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.NewProjectPanel;
import raf.draft.dsw.gui.swing.tree.view.DraftTree;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

public class NodeFactory {

    public DraftNode makenode(DraftNode parent, String name, String autor, String path)
    {
        if (parent instanceof ProjectExplorer)
        {
            return  new Project(name,autor,path, parent);
        }
        else if (parent instanceof Project)
        {
            if (MainFrame.getInstance().getNewProjectPanel().getR2().isSelected())
            {
                return new Building(name,parent);
            }
            else
            {
                Room r=new Room(name,parent);
                    r.setBuilding(null);

                return r;
            }
        }
        else if(parent instanceof Building)
        {
            Room r=new Room(name,parent);
            r.setBuilding(parent);
            return r;
        }
        return null;
    }
}
