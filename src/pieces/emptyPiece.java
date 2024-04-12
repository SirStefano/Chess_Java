package pieces;

import pieces.components.chessVector;
import pieces.components.color;

public class emptyPiece extends prebuildPiece{

    public emptyPiece(chessVector _pieceVector) {
        super(_pieceVector, color.NONE);
    }

    public emptyPiece deepClone(){
        return new emptyPiece(pieceVector.deepClone());
    }
}
