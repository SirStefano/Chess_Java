package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;

public class knight extends prebuildChessPiece {
    public knight(chessVector _pieceVector, color _Color){
        super(_pieceVector,"Knight", _Color);
        letter = 'N';
    }

    public knight deepClone(){
        return new knight(pieceVector.deepClone(), pieceColor);
    }
}
