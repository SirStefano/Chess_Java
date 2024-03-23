package modifiedComponents;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class panelWithBackground extends JPanel {
    protected BufferedImage image;
    public panelWithBackground(LayoutManager layout ,String filePath){
        super(layout);
        try {
            image = ImageIO.read(getClass().getResource(filePath));
        } catch (Exception ex) {
            System.out.println(ex);
            image = null;
        }
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
