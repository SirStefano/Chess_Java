package pieces.chessPieces;

import pieces.components.chessVector;
import pieces.components.color;
import pieces.prebuildChessPiece;

public class queen extends prebuildChessPiece {
    public queen(chessVector _pieceVector, color _Color){
        super(_pieceVector, "Queen", _Color);
        letter = 'Q';
    }
}
