package raf.draft.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.controller.actions.ActionManager;
import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.factories.RoomElementFactory;
import raf.draft.dsw.gui.swing.tab.Tab;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTree;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Getter
@Setter
public class MainFrame extends JFrame implements ISubscriber{
    private static MainFrame instanceMainFrame;
    private ActionManager actionManager;
    private DraftTree draftTree;
    private String lastpath;
    private Tab tabs;
    private JTree projectExplorer;
    private NewProjectPanel newProjectPanel;
    private RoomElementFactory roomElementFactory;
    private MyToolBar toolBar;

    private MainFrame(){
        draftTree=new DraftTreeImplementation();
        actionManager = new ActionManager();
        roomElementFactory=new RoomElementFactory();
        initialize();
    }
    public static MainFrame getInstance()
    {
        if (instanceMainFrame==null)
            instanceMainFrame=new MainFrame();
        return instanceMainFrame;
    }

    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize((int) (screenWidth * 0.85), (int) (screenHeight * 0.85));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new MyToolBar(actionManager);
        add(toolBar, BorderLayout.NORTH);

        projectExplorer = draftTree.generateTree(ApplicationFramework.getInstance().getDraftRepository().getProjectExplorer());

        tabs = new Tab();
        add(tabs, BorderLayout.WEST);
        tabs.setVisible(true);
        draftTree.addSubsriber(tabs);

        projectExplorer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DraftTreeItem node = getDraftTree().getSelectedNode();
                if (node.getDraftNode() instanceof Project)
                {
                if (node!=null && !((Project)node.getDraftNode()).isTabsOpenFlag()) {


                    String projectName = "";
                    if (node.getDraftNode() instanceof Project && e.getClickCount() == 2) {
                        projectName = node.getDraftNode().getName() + " ";
                        ((Project) node.getDraftNode()).setTabsOpenFlag(true);
                        List<DraftTreeItem> leafNodes = getDraftTree().getLeafNodes(node);

                        tabs.addTab(leafNodes, projectName);
                    }
                }
            }
            }

        });

        JScrollPane scroll=new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(200,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,tabs);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
        StatesButtonsPane statesButtonsPane=new StatesButtonsPane();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(Box.createVerticalGlue());
        rightPanel.add(statesButtonsPane);
        rightPanel.add(Box.createVerticalGlue());
        add(rightPanel, BorderLayout.EAST);


    }

    @Override
    public void updateMessage(String message) {
        JOptionPane jop = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE);
        jop.showMessageDialog(this, message);
    }
}
