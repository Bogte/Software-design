package raf.draft.dsw.gui.swing.tab;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.draft.dsw.factories.PainterFactory;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.RoomElement;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.Room;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
public class Tab extends JTabbedPane implements TabAdd {

    private Map<SingleTab, JPanel> tabMap = new HashMap<>();
    private Color color;
    private List<SingleTab> tabs=new ArrayList<>();
    @Override
    public void addTab(List<DraftTreeItem> leafNodes, String projectName) {
        String tmp = "";
        String autor = "";
        for(DraftTreeItem treeItem : leafNodes) {
            SingleTab tab=new SingleTab();
            JPanel jp = new JPanel(new BorderLayout());
            jp.setName(treeItem.toString());
            if(treeItem.getDraftNode() instanceof Room) {

                if(treeItem.getDraftNode().getParent() instanceof Building){
                    Project p = (Project) treeItem.getDraftNode().getParent().getParent();
                    tab.setProject(p);
                    tab.setName(treeItem.getDraftNode().getName());
                    tab.setRoom((Room) treeItem.getDraftNode());
                    tmp = tab.getProject().toString() + " Building name: " + treeItem.getDraftNode().getParent().getName();
                    tab.setBuilding((Building) treeItem.getDraftNode().getParent());
                    autor = p.getAutor();
                    tab.setAutor(autor);
                    color=((Building) treeItem.getDraftNode().getParent()).getColor();
                }
                else {
                    Project p = (Project) treeItem.getDraftNode().getParent();
                    tab.setProject(p);
                    tab.setRoom((Room) treeItem.getDraftNode());
                    tab.setName(treeItem.getDraftNode().getName());
                    tmp = tab.getProject().toString() + " Building name: /";
                    tab.setBuilding(null);
                    autor= p.getAutor();
                    tab.setAutor(autor);
                    color=Color.white;
                }


                if(!tabMap.containsKey(tab)) {
                    tabMap.put(tab, jp);
                    JLabel project = new JLabel();
                    JLabel autorLb = new JLabel();
                    autorLb.setText("Autor's name: " + autor);
                    project.setText("Project name: " + tmp);
                    RoomView roomView=new RoomView(new BorderLayout());
                    tab.setRoomView(roomView);
                    MainFrame.getInstance().getDraftTree().addSubsriber(roomView);
                    tabs.add(tab);
                    jp.add(project, BorderLayout.NORTH);
                    jp.add(autorLb, BorderLayout.SOUTH);
                    jp.add(roomView,BorderLayout.CENTER);

                    addTab(treeItem.toString(), jp);
                    setBackgroundAt(getTabCount() - 1, color);
                    if (!tab.getRoom().getChildren().isEmpty())
                    {
                        tab.getRoomView().setRoomDimensions(tab.getRoom().getCmLenght(),tab.getRoom().getCmHeight());
                        roomView.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        PainterFactory painterFactory=new PainterFactory();
                        for (DraftNode roomElement:tab.getRoom().getChildren())
                        {
                            Painter painter=painterFactory.PainterFactory((RoomElement) roomElement);
                            tab.getRoomView().getPainters().add(painter);
                        }
                    }

                }
            }
        }
    }

    @Override
    public void refreshTabs() {
        this.removeAll();
        for (SingleTab singleTab:tabs)
        {
            String tmp="";
            JPanel jp = new JPanel(new BorderLayout());
            jp.setName(singleTab.getName());
            JLabel project = new JLabel();
            JLabel autorLb = new JLabel();
            if (singleTab.getBuilding()!=null)
            {
                tmp = singleTab.getProject().toString() + " Building name: " + singleTab.getBuilding().getName();
                //System.out.println(singleTab.getBuilding().getName());
                color=singleTab.getBuilding().getColor();
            }
            else
            {
                tmp = singleTab.getProject().toString() + " Building name: /";
                color=Color.white;
            }
            autorLb.setText("Autor's name: " + singleTab.getProject().getAutor());
            project.setText("Project name: " + tmp);
            jp.add(project, BorderLayout.NORTH);
            jp.add(autorLb, BorderLayout.SOUTH);
            jp.add(singleTab.getRoomView(),BorderLayout.CENTER);
            this.addTab(singleTab.getName(),jp);
            setBackgroundAt(getTabCount() - 1, color);
        }
    }
}
