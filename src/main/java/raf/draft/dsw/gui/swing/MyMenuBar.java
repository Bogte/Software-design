package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.*;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        ExitAction ea = new ExitAction();
        fileMenu.add(ea);

        AboutUs au = new AboutUs();
        fileMenu.add(au);

        NewProjectAction npa = new NewProjectAction();
        fileMenu.add(npa);

        DeleteFileAction dfa=new DeleteFileAction();
        fileMenu.add(dfa);

        ChangeFileInfoAction cfia=new ChangeFileInfoAction();
        fileMenu.add(cfia);

        add(fileMenu);
    }
}
