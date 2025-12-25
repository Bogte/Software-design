package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {

    private static AboutUsFrame instanceAboutUsFrame;

    private AboutUsFrame() {
        initialize();
    }

    public static AboutUsFrame getInstanceAboutUsFrame() {
        if (instanceAboutUsFrame == null) {
            instanceAboutUsFrame = new AboutUsFrame();
        }
        return instanceAboutUsFrame;
    }

    private void initialize(){
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setTitle("About Us");
        setLayout(new GridBagLayout());

        JTextPane Jovan = new JTextPane();
        JTextPane Filip = new JTextPane();

        ImageIcon icon = new ImageIcon("src/main/resources/images/Filip.jpg");

        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);

        JLabel slikaF = new JLabel(icon);

        ImageIcon iconJ = new ImageIcon("src/main/resources/images/Jovan.jpg");

        Image scaledImageJ = iconJ.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        iconJ = new ImageIcon(scaledImageJ);

        JLabel slikaJ = new JLabel(iconJ);

        Jovan.setText("Jovan Nikolic SI 71/2024");
        Jovan.setEditable(false);
        Filip.setText("Filip Lucic SI 117/2024");
        Filip.setEditable(false);

        JPanel panelTop = new JPanel(new GridBagLayout());
        JPanel panelBottom = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;

        panelTop.add(Jovan, c);
        panelBottom.add(Filip, c);

        c.insets = new Insets(20, 10, 20, 10);

        add(panelTop, c);
        c.gridy = 1;
        add(slikaJ, c);
        c.gridy = 2;
        add(slikaF, c);
        c.gridy = 3;
        add(panelBottom, c);
    }

}
