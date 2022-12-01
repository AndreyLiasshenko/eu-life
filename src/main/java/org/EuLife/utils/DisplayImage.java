package org.EuLife.utils;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayImage {
    public static void show(InputStream inputStream) throws IOException {
        BufferedImage img= ImageIO.read(inputStream);
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();

        frame.setLayout(new FlowLayout());
        frame.setSize(img.getWidth(),img.getHeight());

        JLabel lbl=new JLabel();
        lbl.setIcon(icon);

        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
