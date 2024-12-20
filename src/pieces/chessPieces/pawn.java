package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;
public class pawn extends prebuildChessPiece{
    public pawn(chessVector _pieceVector, color _Color){
        super(_pieceVector, "Pawn", _Color);
        letter = Character.MIN_VALUE;
    }

    public pawn deepClone(){
        return new pawn(pieceVector.deepClone(), pieceColor);
    }
}
