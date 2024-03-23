package partsOfWindow;

import javax.imageio.ImageIO;
import javax.swing.*;

import partsOfWindow.partsOfChessBoard.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class chessBoard extends JPanel{
    private board chessBoardImage;
    private panelsWithCoordinates vertical;
    private panelsWithCoordinates horizontal;
    public chessBoard(){
        super(null);
        setBounds(0, 0, 542,542); //do poprawy
        init();
    }
    private void init(){
        chessBoardImage = new board();
        add(chessBoardImage);
        vertical = new panelsWithCoordinates('1');
        vertical.setBounds(0, 0, 60, 482);
        vertical.rotate();
        add(vertical);
        horizontal = new panelsWithCoordinates('A');
        horizontal.setBounds(60, 482, 482, 60);
        add(horizontal);
    }

}
