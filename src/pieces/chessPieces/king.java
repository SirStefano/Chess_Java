package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;

import java.awt.*;

public class king extends prebuildChessPiece {
    public king(chessVector _pieceVector, color _Color){
        super(_pieceVector,"King", _Color);
        setBackground(Color.red);
        letter = 'K';
    }

    @Override
    public void killBorders() {
        setOpaque(true);
    }

    public king deepClone(){
        return new king(pieceVector.deepClone(), pieceColor);
    }
}
