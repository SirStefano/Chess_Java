package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;

public class king extends prebuildChessPiece {
    public king(chessVector _pieceVector, color _Color){
        super(_pieceVector,"King", _Color);
        letter = 'K';
    }

    @Override
    public void killBorders() {
    }
}
