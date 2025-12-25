package raf.draft.dsw.gui.swing.tab;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raf.draft.dsw.controller.prototype.Prototype;
import raf.draft.dsw.controller.states.*;
import raf.draft.dsw.controller.states.StateObserver.SISubscriber;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.model.structures.Room;
import raf.draft.dsw.model.structures.elements.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoomView extends JPanel implements SISubscriber {
    private List<Prototype> items=new ArrayList<>();
    private final int offset=20;
    private double roomWidth;
    private double roomHeight;
    private double aspectRatio;
    private StateManager stateManager=new StateManager();
    private List<Painter> painters=new ArrayList<>();
    private List<Painter> selectedPainters=new ArrayList<>();
    private Rectangle selectRectangle=new Rectangle();
    public RoomView(LayoutManager layout) {
        super(layout);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (roomHeight!=0 && roomWidth!=0) {
                    if (stateManager.getCurrState() instanceof AddState) {
                        ((AddState) stateManager.getCurrState()).setLastClickPositionX(e.getX());
                        ((AddState) stateManager.getCurrState()).setLastClickPositionY(e.getY());
                    }
                    stateManager.getCurrState().myMouseClick(e);

                }
                else if (stateManager.getCurrState() instanceof EditRoomState)
                {
                    stateManager.getCurrState().myMouseClick(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (roomHeight!=0 && roomWidth!=0) {
                    stateManager.getCurrState().myMousePressed(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (roomHeight!=0 && roomWidth!=0) {
                    stateManager.getCurrState().MyMouseRelease(e);
                }
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (roomHeight!=0 && roomWidth!=0) {
                    stateManager.getCurrState().myMouseDrag(e);
                }
            }
        });
        this.addMouseWheelListener(e->
        {
            if (roomHeight!=0 && roomWidth!=0) {
                stateManager.getCurrState().myMouseWheelMoved(e);
            }
        });
    }
    public void setSelectedRectangleNum(int startX,int startY,int endX,int endY)
    {
        int px = Math.min(startX,endX);
        int py = Math.min(startY,endY);
        int pw=Math.abs(startX-endX);
        int ph=Math.abs(startY-endY);
        selectRectangle=new Rectangle(px,py,pw,ph);

        repaint();
    }
    public void copySelectedItems()
    {
        List<Prototype> copiedItems=new ArrayList<>();
        List<Prototype> deleteDuplicates=new ArrayList<>();
        for (Prototype item:items)
        {
            Prototype copiedItem=item.clone();
            deleteDuplicates.add(item);
            if (copiedItem instanceof Bathtub)
            {
                Bathtub bathtub=(Bathtub) copiedItem;
                bathtub.setLocationX(bathtub.getLocationX()+offset);
                bathtub.setLocationY(bathtub.getLocationY()+offset);
                copiedItems.add(bathtub);
            } else if (copiedItem instanceof Bed) {
                Bed bed=(Bed) copiedItem;
                bed.setLocationX(bed.getLocationX()+offset);
                bed.setLocationY(bed.getLocationY()+offset);
                copiedItems.add(bed);
            }else if (copiedItem instanceof Boiler) {
                Boiler boiler=(Boiler) copiedItem;
                boiler.setLocationX(boiler.getLocationX()+offset);
                boiler.setLocationY(boiler.getLocationY()+offset);
                copiedItems.add(boiler);
            }else if (copiedItem instanceof Closet) {
                Closet closet=(Closet) copiedItem;
                closet.setLocationX(closet.getLocationX()+offset);
                closet.setLocationY(closet.getLocationY()+offset);
                copiedItems.add(closet);
            }else if (copiedItem instanceof Door) {
                Door door=(Door) copiedItem;
                door.setLocationX(door.getLocationX()+offset);
                door.setLocationY(door.getLocationY()+offset);
                copiedItems.add(door);
            }else if (copiedItem instanceof Sink) {
                Sink sink=(Sink) copiedItem;
                sink.setLocationX(sink.getLocationX()+offset);
                sink.setLocationY(sink.getLocationY()+offset);
                copiedItems.add(sink);
            }else if (copiedItem instanceof Table) {
                Table table=(Table) copiedItem;
                table.setLocationX(table.getLocationX()+offset);
                table.setLocationY(table.getLocationY()+offset);
                copiedItems.add(table);
            }else if (copiedItem instanceof Toilet) {
                Toilet toilet=(Toilet) copiedItem;
                toilet.setLocationX(toilet.getLocationX()+offset);
                toilet.setLocationY(toilet.getLocationY()+offset);
                copiedItems.add(toilet);
            }else if (copiedItem instanceof WashingMachine) {
                WashingMachine washingMachine=(WashingMachine) copiedItem;
                washingMachine.setLocationX(washingMachine.getLocationX()+offset);
                washingMachine.setLocationY(washingMachine.getLocationY()+offset);
                copiedItems.add(washingMachine);
            }
        }
        items.removeAll(deleteDuplicates);
        items.addAll(copiedItems);

    }
    public void setRoomDimensions(double width, double height) {
        this.roomWidth = width;
        this.roomHeight = height;
        MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom().setCmHeight(height);
        MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoom().setCmLenght(width);
         this.aspectRatio= width / height;
        double panelWidth = this.getWidth();
        double panelHeight = this.getHeight();
        double aspectRatioPanel = panelWidth / panelHeight;
        if (this.aspectRatio > aspectRatioPanel) {
            this.roomWidth = panelWidth;
            this.roomHeight = panelWidth / this.aspectRatio;
        } else {
            this.roomHeight = panelHeight;
            this.roomWidth = panelHeight * this.aspectRatio;
        }
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
            for (Painter p:painters)
            {
                p.paint(g2);
            }

            if (selectRectangle != null) {
                g2.setColor(new Color(0, 0, 255, 50));
                g2.fill(selectRectangle);
                g2.setColor(Color.BLUE);
                g2.draw(selectRectangle);
            }

    }

    @Override
    public void refreshRoomViews() {
        this.repaint();
    }
}
