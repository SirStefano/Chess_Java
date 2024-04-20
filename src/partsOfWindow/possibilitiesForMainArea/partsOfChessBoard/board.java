package partsOfWindow.possibilitiesForMainArea.partsOfChessBoard;

import pieces.chessPieces.*;
import modifiedComponents.panelWithBackground;
import pieces.components.chessVector;
import pieces.components.color;
import pieces.emptyPiece;
import pieces.prebuildPiece;

import java.awt.*;

public class board extends panelWithBackground {
    private prebuildPiece[][] pieces = new prebuildPiece[8][8];
    public board(){
        super(new GridLayout(8, 8), "../../../chess_board/board.png");
        setBounds(60, 0, 482, 482);
        init();
        showPieces(color.WHITE);
    }
    public void init(){
        pieces[0][7] = new rock(new chessVector(0,7), color.BLACK);
        pieces[7][7] = new rock(new chessVector(7,7),color.BLACK);
        pieces[1][7] = new knight(new chessVector(1,7),color.BLACK);
        pieces[6][7] = new knight(new chessVector(6,7),color.BLACK);
        pieces[2][7] = new bishop(new chessVector(2,7),color.BLACK);
        pieces[5][7] = new bishop(new chessVector(5,7),color.BLACK);
        pieces[3][7] = new queen(new chessVector(3,7),color.BLACK);
        pieces[4][7] = new king(new chessVector(4,7),color.BLACK);
        for(int x = 0; x<8; ++x){
            pieces[x][6] = new pawn(new chessVector(x, 6), color.BLACK);
        }
        for(int y = 5; y>1; --y){
            for(int x = 0; x<8; ++x){
                pieces[x][y] = new emptyPiece(new chessVector(x, y));
            }
        }
        for(int x = 0; x<8; ++x){
            pieces[x][1] = new pawn(new chessVector(x, 1), color.WHITE);
        }
        pieces[0][0] = new rock(new chessVector(0,0),color.WHITE);
        pieces[7][0] = new rock(new chessVector(7,0),color.WHITE);
        pieces[1][0] = new knight(new chessVector(1,0),color.WHITE);
        pieces[6][0] = new knight(new chessVector(6,0),color.WHITE);
        pieces[2][0] = new bishop(new chessVector(2,0),color.WHITE);
        pieces[5][0] = new bishop(new chessVector(5,0),color.WHITE);
        pieces[3][0] = new queen(new chessVector(3,0),color.WHITE);
        pieces[4][0] = new king(new chessVector(4,0),color.WHITE);
    }
    public void showPieces(color side){ //1 for white side
        if(side == color.WHITE){
            for(int y = 7; y>=0; --y){
                for(int x = 0; x<8; ++x){
                    add(pieces[x][y]);
                }
            }
        }else{
            for(int y = 0; y<8; ++y){
                for(int x = 7; x>=0; --x){
                    add(pieces[x][y]);
                }
            }
        }
    }
    public void hidePieces(){
        removeAll();
        revalidate();
        repaint();
    }
    public void clearBorders(){
        for(int x = 0; x<8; ++x){
            for(int y = 0; y<8; ++y){
                pieces[x][y].setBorderPainted(false);
            }
        }
    }
    public prebuildPiece[][] getPieces(){
        return pieces;
    }
}
