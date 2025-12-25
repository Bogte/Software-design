package raf.draft.dsw.controller.states;

import raf.draft.dsw.controller.states.statesActions.SEditAction;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.MainFrame;
import raf.draft.dsw.gui.swing.painters.compositePainters.BasicPainter;
import raf.draft.dsw.gui.swing.painters.compositePainters.Painter;
import raf.draft.dsw.gui.swing.tab.RoomView;
import raf.draft.dsw.model.messages.MessageType;

import java.awt.event.*;

public class EditState implements State{
    private  SEditAction sEditAction=new SEditAction();;
    @Override
    public void run(RoomView roomView, MouseEvent e) {
        try {
            String sLenght = sEditAction.getEditStatePanel().getTf1().getText();
            String sHeight = sEditAction.getEditStatePanel().getTf2().getText();
            int lenght = (int) (Integer.parseInt(sLenght) * roomView.getAspectRatio());
            int height = (int) (Integer.parseInt(sHeight) * roomView.getAspectRatio());
            for (Painter p : roomView.getPainters()) {
                if (p.elementAt(e.getPoint())) {
                    ((BasicPainter) p).getRoomElement().setCmLenght(lenght);
                    ((BasicPainter) p).getRoomElement().setCmHeight(height);
                    break;
                }
            }
            roomView.repaint();
        }catch (NumberFormatException|IndexOutOfBoundsException|NullPointerException p)
        {
            ApplicationFramework.getInstance().getMessageGenerator().newMassage(MessageType.ERROR,"Nisu uneti svi podaci");
        }

    }

    @Override
    public void myMouseClick(MouseEvent e) {
        sEditAction.actionPerformed();
        sEditAction.getEditStatePanel().getBt().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent p) {
                run(MainFrame.getInstance().getTabs().getTabs().get(MainFrame.getInstance().getTabs().getSelectedIndex()).getRoomView(), e);
                sEditAction.getEditStatePanel().setVisible(false);
            }
        });

    }

    @Override
    public void myMouseDrag(MouseEvent e) {

    }

    @Override
    public void myMousePressed(MouseEvent e) {

    }

    @Override
    public void myKeyPressed(KeyEvent e) {

    }

    @Override
    public void myMouseWheelMoved(MouseWheelEvent e) {

    }

    @Override
    public void MyMouseRelease(MouseEvent e) {

    }
}
