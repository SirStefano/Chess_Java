package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;

public class rock extends prebuildChessPiece{
    public rock(chessVector _pieceVector, color _Color){
        super(_pieceVector, "Rock", _Color);
        letter = 'R';
    }
}
