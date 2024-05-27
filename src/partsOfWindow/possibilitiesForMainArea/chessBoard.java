package partsOfWindow.possibilitiesForMainArea;

import javax.swing.*;

import partsOfWindow.mainArea;
import partsOfWindow.possibilitiesForMainArea.partsOfChessBoard.*;
import partsOfWindow.possibilitiesForMainArea.partsOfChessBoard.movesEngine.mainEngine;
import pieces.components.color;
import pieces.prebuildChessPiece;
import pieces.prebuildPiece;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class chessBoard extends JPanel implements MouseListener {
    private final mainArea precursor;
    private board chessBoardImage;
    private panelsWithCoordinates vertical;
    private panelsWithCoordinates horizontal;
    private color side;
    private panelWithMoves chessNotation;
    public chessBoard(mainArea area){
        super(null);
        precursor = area;
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

        chessNotation = new panelWithMoves();
        JScrollPane panelWithScroll = new JScrollPane(chessNotation);
        panelWithScroll.setBounds(542, 0, 120, 482);
        add(panelWithScroll);
    }
    private void changeSide(){
        if(side == color.WHITE)
            side = color.BLACK;
        else
            side = color.WHITE;
    }
    public void mouseEntered(MouseEvent evt) {
    }

    public void mouseExited(MouseEvent evt) {
    }
    public void mouseClicked(MouseEvent evt) {
        prebuildPiece button = (prebuildPiece) evt.getComponent();
        if(button.pieceColor == side){
            chessBoardImage.clearBorders();
            mainEngine.checkInstanceOf((prebuildChessPiece) button, chessBoardImage.getPieces());
            chessBoardImage.hidePieces();
            chessBoardImage.showPieces(side);
        }else if(button.isBorderPainted()){
            if(mainEngine.makingMove(button, chessBoardImage.getPieces(),
                    chessNotation.getWhiteMoves(), chessNotation.getBlackMoves())) {
                chessBoardImage.clearBorders();
                chessBoardImage.hidePieces();
                chessBoardImage.showPieces(side);
                changeSide();
                chessBoardImage.clearBorders();
                chessBoardImage.hidePieces();
                horizontal.rotate();
                vertical.rotate();
                chessBoardImage.showPieces(side);
                if(mainEngine.check(side, mainEngine.copyChessBoard(chessBoardImage.getPieces()))){
                    if(mainEngine.pat(chessBoardImage.getPieces(), side)){
                        System.out.println("Mat");
                        precursor.initEndGameMenu("Check mate");
                    }
                    chessBoardImage.getPieces()[mainEngine.findKing(chessBoardImage.getPieces(), side).getX()]
                            [mainEngine.findKing(chessBoardImage.getPieces(), side).getY()].setOpaque(true);
                }
                chessNotation.newMove();
            }
        }
    }
    public void mousePressed(MouseEvent evt) {
    }
    public void mouseReleased(MouseEvent evt) {
    }

}
