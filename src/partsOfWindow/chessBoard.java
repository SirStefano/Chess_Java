package partsOfWindow;

import javax.imageio.ImageIO;
import javax.swing.*;

import partsOfWindow.partsOfChessBoard.*;
import partsOfWindow.partsOfChessBoard.movesEngine.mainEngine;
import pieces.components.color;
import pieces.prebuildChessPiece;
import pieces.prebuildPiece;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class chessBoard extends JPanel implements MouseListener {
    private board chessBoardImage;
    private panelsWithCoordinates vertical;
    private panelsWithCoordinates horizontal;
    private color side;
    public chessBoard(){
        super(null);
        setBounds(0, 0, 542,542); //do poprawy
        init();
    }
    private void init(){
        side = color.WHITE; //chwilowo
        chessBoardImage = new board();
        add(chessBoardImage);

        prebuildPiece[][] pieces = chessBoardImage.getPieces();
        for(int i = 0; i<8; ++i){
            for(int i2 = 0; i2<8; ++i2){
                pieces[i][i2].addMouseListener(this);
            }
        }

        vertical = new panelsWithCoordinates('1');
        vertical.setBounds(0, 0, 60, 482);
        vertical.rotate();
        add(vertical);
        horizontal = new panelsWithCoordinates('a');
        horizontal.setBounds(60, 482, 482, 60);
        add(horizontal);
    }
    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }
    public void mouseClicked(MouseEvent evt) {
        prebuildPiece button = (prebuildPiece) evt.getComponent();
        if(button.pieceColor == side){
            //te przyciski bazowo aktywne
            chessBoardImage.clearBorders();
            mainEngine.checkInstanceOf((prebuildChessPiece) button, chessBoardImage.getPieces());
            chessBoardImage.hidePieces();
            chessBoardImage.showPieces(true);
        }else if(button.isBorderPainted()){
            //ruch pionka
            mainEngine.makingMove(button, chessBoardImage.getPieces());
            chessBoardImage.clearBorders();
            chessBoardImage.hidePieces();
            chessBoardImage.showPieces(true);
        }else{
            //nic skoro nie pasuje do kolora ani nie jest polem wyboru (:
        }
    }
    public void mousePressed(MouseEvent evt) {
    }
    public void mouseReleased(MouseEvent evt) {
    }

}
