package partsOfWindow.partsOfChessBoard;

import modifiedComponents.panelWithBackground;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class board extends panelWithBackground{
    public board(){
        super(new GridLayout(8, 8), "../../chess_board/board.png"); //do zmiany na GridBag jak będą dodawane przyciski
        setBounds(60, 0, 482, 482);
    }
}
