package partsOfWindow.partsOfChessBoard.movesEngine;

import pieces.prebuildChessPiece;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class buttonsHandler extends MouseAdapter {
    private prebuildChessPiece focusPiece;
    private final choosePromotionPiece window;
    public buttonsHandler(prebuildChessPiece _focusPiece, choosePromotionPiece _window){
        focusPiece = _focusPiece;
        window = _window;
    }

    public void mouseEntered(MouseEvent evt) {
        prebuildChessPiece piece = (prebuildChessPiece) evt.getComponent();
        piece.setContentAreaFilled(true);
    }

    public void mouseExited(MouseEvent evt) {
        prebuildChessPiece piece = (prebuildChessPiece) evt.getComponent();
        piece.setContentAreaFilled(false);
    }

    public void mouseClicked(MouseEvent evt) {
        focusPiece = changePiece((prebuildChessPiece) evt.getComponent());
        window.dispose();
    }
    public prebuildChessPiece changePiece(prebuildChessPiece piece){
        piece.getPieceVector().setX(focusPiece.getPieceVector().getX());
        piece.getPieceVector().setY(focusPiece.getPieceVector().getY());
        piece.removeMouseListener(this);
        piece.setContentAreaFilled(false);
        for(MouseListener x: focusPiece.getMouseListeners()){
            piece.addMouseListener(x);
        }
        return piece;
    }
    public prebuildChessPiece getFocusPiece(){
        return focusPiece;
    }
}
