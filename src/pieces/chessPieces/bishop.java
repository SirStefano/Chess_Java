package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;

public class bishop extends prebuildChessPiece{
    public bishop(chessVector _pieceVector , color _Color){
        super(_pieceVector, "Bishop", _Color);
        letter = 'B';
    }
    public bishop deepClone(){
        return new bishop(pieceVector.deepClone(), pieceColor);
    }
}
