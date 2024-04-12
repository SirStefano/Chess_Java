package pieces;

import javax.swing.*;
import java.awt.*;
import pieces.components.chessVector;
import pieces.components.color;

public abstract class prebuildPiece extends JButton {
    protected chessVector pieceVector;
    public final color pieceColor;
    public prebuildPiece(chessVector _pieceVector, color _Color){
        pieceVector = _pieceVector;
        pieceColor = _Color;

        setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3, true));
        setBackground(Color.PINK);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }
    public chessVector getPieceVector(){
        return pieceVector;
    }
    public void setBorders(){
        setBorderPainted(true);
    }
    public void clearBorders(){
        setBorderPainted(false);
    }
    public abstract prebuildPiece deepClone();
}
