package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.states.statesActions.*;
import raf.draft.dsw.controller.template.Save;
import raf.draft.dsw.controller.template.Upload;

import javax.swing.*;
import java.awt.*;

public class StatesButtonsPane extends JPanel {

    public StatesButtonsPane() {
        JToolBar verticalToolBar = new JToolBar(JToolBar.VERTICAL);

        verticalToolBar.setFloatable(false);

        setLayout(new BorderLayout());
        add(verticalToolBar, BorderLayout.CENTER);

        JButton button1 = new JButton();
        button1.setAction(new SEditRoomAction());
        verticalToolBar.add(button1);

        JButton button2 = new JButton();
        button2.setAction(new SAddAction());
        verticalToolBar.add(button2);

        JButton button3 = new JButton();
        button3.setAction(new SSelectAction());
        verticalToolBar.add(button3);

        JButton button4 = new JButton();
        button4.setAction(new SResizeAction());
        verticalToolBar.add(button4);

        JButton button5 = new JButton();
        button5.setAction(new RotateLeftAction());
        verticalToolBar.add(button5);

        JButton button6 = new JButton();
        button6.setAction(new RotateRightAction());
        verticalToolBar.add(button6);

        JButton button7 = new JButton();
        button7.setAction(new SDeleteAction());
        verticalToolBar.add(button7);

        JButton button8 = new JButton();
        button8.setAction(new CopyAction());
        verticalToolBar.add(button8);

        JButton button9 = new JButton();
        button9.setAction(new PasteAction());
        verticalToolBar.add(button9);

        JButton button10 = new JButton();
        button10.setAction(new SEditStateStart());
        verticalToolBar.add(button10);

        JButton button11 = new JButton();
        button11.setAction(new MoveAction());
        verticalToolBar.add(button11);

        JButton button12 = new JButton();
        button12.setAction(new SZoomAction());
        verticalToolBar.add(button12);

        JButton button13 = new JButton();
        button13.setAction(new Save());
        verticalToolBar.add(button13);

        JButton button14 = new JButton();
        button14.setAction(new Upload());
        verticalToolBar.add(button14);


        verticalToolBar.setLayout(new BoxLayout(verticalToolBar, BoxLayout.Y_AXIS));
        verticalToolBar.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
}
