package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;
public class pawn extends prebuildChessPiece{
    public pawn(chessVector _pieceVector, color _Color){
        super(_pieceVector, "Pawn", _Color);
        letter = '0';
    }
}
