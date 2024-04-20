package partsOfWindow.possibilitiesForMainArea.partsOfChessBoard.movesEngine;

import pieces.chessPieces.*;
import pieces.components.chessVector;
import pieces.components.color;
import pieces.emptyPiece;
import pieces.prebuildChessPiece;
import pieces.prebuildPiece;

import java.awt.event.MouseListener;

public class mainEngine {
    private static prebuildChessPiece focusPiece;
    static chessVector[] possibleMovesKnight= {new chessVector(2,-1),new chessVector(1,2), new chessVector(-2,1),new chessVector(-1,-2)
    ,new chessVector(2,1),new chessVector(-1,2),new chessVector(-2,-1),new chessVector(1,-2)};
    static chessVector[] possibleMovesBishop= {new chessVector(1,1),new chessVector(-1,-1), new chessVector(1,-1),new chessVector(-1,1)};
    public static void checkInstanceOf(prebuildChessPiece piece, prebuildPiece[][] pieces){
        focusPiece = piece;
        if(piece instanceof pawn){
            pawnMove((pawn)piece, pieces);
        }else if(piece instanceof rock){
            rockMove((rock)piece, pieces);
        }else if(piece instanceof knight){
            knightMove((knight) piece, pieces);
        }else if(piece instanceof bishop){
            bishopMove((bishop) piece, pieces);
        }else if(piece instanceof queen){
            queenMove((queen)piece, pieces);
        }else if(piece instanceof king){
            kingMove((king) piece, pieces);
        }
    }
    public static boolean makingMove(prebuildPiece piece, prebuildPiece[][] pieces){
        if(!checkCheckWithForwardMove(pieces, focusPiece.getPieceVector() ,piece.getPieceVector(), focusPiece.pieceColor)) {
            if (focusPiece instanceof pawn && (piece.getPieceVector().getY() == 7 || piece.getPieceVector().getY() == 0)) {
                choosePromotionPiece chooseWindow = new choosePromotionPiece(focusPiece.pieceColor);
                buttonsHandler forWindow = new buttonsHandler(focusPiece, chooseWindow);
                for (int i = 0; i < 4; ++i) {
                    chooseWindow.getButtons()[i].addMouseListener(forWindow);
                }
                chooseWindow.setLocationRelativeTo(focusPiece);
                chooseWindow.setModal(true);
                chooseWindow.setVisible(true);
                focusPiece = forWindow.getFocusPiece();
                if (focusPiece instanceof pawn) {
                    focusPiece = forWindow.changePiece(new queen(new chessVector(0, 0), focusPiece.pieceColor));
                }
            }
            pieces[piece.getPieceVector().getX()][piece.getPieceVector().getY()] = focusPiece;
            pieces[focusPiece.getPieceVector().getX()][focusPiece.getPieceVector().getY()]
                    = new emptyPiece(new chessVector(focusPiece.getPieceVector().getX(), focusPiece.getPieceVector().getY()));
            for (MouseListener x : focusPiece.getMouseListeners()) {
                pieces[focusPiece.getPieceVector().getX()][focusPiece.getPieceVector().getY()].addMouseListener(x);
            }
            focusPiece.getPieceVector().setX(piece.getPieceVector().getX());
            focusPiece.getPieceVector().setY(piece.getPieceVector().getY());
            return true;
        }
        return false;
    }
    public static void pawnMove(pawn piece, prebuildPiece[][] pieces){
        chessVector pawnVector = piece.getPieceVector();
        int y;
        if(piece.pieceColor == color.WHITE){
            y = 1;
        }else{
            y = -1;
        }
        if(checkBoardBorders(new chessVector(pawnVector.getX() + 1, pawnVector.getY() + y))) {
            if (enemy(new chessVector(pawnVector.getX() + 1, pawnVector.getY() + y), pieces, piece.pieceColor)) {
                prebuildChessPiece momentalPiece = (prebuildChessPiece) pieces[pawnVector.getX() + 1][pawnVector.getY() + y];
                momentalPiece.killBorders();
            }
        }
        if(checkBoardBorders(new chessVector(pawnVector.getX()-1,pawnVector.getY()+y))) {
            if (enemy(new chessVector(pawnVector.getX() - 1, pawnVector.getY() + y), pieces, piece.pieceColor)) {
                prebuildChessPiece momentalPiece = (prebuildChessPiece) pieces[pawnVector.getX() - 1][pawnVector.getY() + y];
                momentalPiece.killBorders();
            }
        }
        if(checkBoardBorders(new chessVector(pawnVector.getX(), pawnVector.getY() + y))) {
            if (emptySquare(new chessVector(pawnVector.getX(), pawnVector.getY() + y), pieces, piece.pieceColor)) {
                pieces[pawnVector.getX()][pawnVector.getY() + y].setBorders();
            }
        }
        if((pawnVector.getY() == 1 || pawnVector.getY() == 6) && checkBoardBorders(new chessVector(pawnVector.getX(), pawnVector.getY() + 2 * y))) {
            if (emptySquare(new chessVector(pawnVector.getX(), pawnVector.getY() + 2 * y), pieces, piece.pieceColor)) {
                pieces[pawnVector.getX()][pawnVector.getY() + 2 * y].setBorders();
            }
        }
    }
    public static void rockMove(rock piece, prebuildPiece[][] pieces){
        linearMove(piece, pieces);
    }
    private static void linearMove(prebuildChessPiece piece, prebuildPiece[][] pieces){
        chessVector pieceVector = piece.getPieceVector();
        for(int i = pieceVector.getX()+1; i<8; ++i){
            if(emptySquare(new chessVector(i, pieceVector.getY()), pieces, piece.pieceColor)){
                pieces[i][pieceVector.getY()].setBorders();
            }else{
                if(enemy(new chessVector(i, pieceVector.getY()), pieces, piece.pieceColor)){
                    prebuildChessPiece momentalPiece = (prebuildChessPiece) pieces[i][pieceVector.getY()];
                    momentalPiece.killBorders();
                }
                break;
            }
        }
        for(int i = pieceVector.getX()-1; i>=0; --i){
            if(emptySquare(new chessVector(i, pieceVector.getY()), pieces, piece.pieceColor)){
                pieces[i][pieceVector.getY()].setBorders();
            }else{
                if(enemy(new chessVector(i, pieceVector.getY()), pieces, piece.pieceColor)){
                    prebuildChessPiece momentalPiece = (prebuildChessPiece) pieces[i][pieceVector.getY()];
                    momentalPiece.killBorders();
                }
                break;
            }
        }
        for(int i = pieceVector.getY()+1; i<8; ++i){
            if(emptySquare(new chessVector(pieceVector.getX(), i), pieces, piece.pieceColor)){
                pieces[pieceVector.getX()][i].setBorders();
            }else{
                if(enemy(new chessVector(pieceVector.getX(), i), pieces, piece.pieceColor)){
                    prebuildChessPiece momentalPiece = (prebuildChessPiece) pieces[pieceVector.getX()][i];
                    momentalPiece.killBorders();
                }
                break;
            }
        }
        for(int i = pieceVector.getY()-1; i>=0; --i){
            if(emptySquare(new chessVector(pieceVector.getX(), i), pieces, piece.pieceColor)){
                pieces[pieceVector.getX()][i].setBorders();
            }else{
                if(enemy(new chessVector(pieceVector.getX(), i), pieces, piece.pieceColor)){
                    prebuildChessPiece momentalPiece = (prebuildChessPiece) pieces[pieceVector.getX()][i];
                    momentalPiece.killBorders();
                }
                break;
            }
        }
    }
    public static void knightMove(knight piece, prebuildPiece[][] pieces){
        for(chessVector x:possibleMovesKnight){
            if(checkBoardBorders(piece.getPieceVector().add(x))){
                if(enemy(piece.getPieceVector().add(x), pieces, piece.pieceColor)){
                    ((prebuildChessPiece)pieces[piece.getPieceVector().add(x).getX()][piece.getPieceVector().add(x).getY()]).killBorders();
                }else if(!(sameLikeAttacking(piece.getPieceVector().add(x), pieces, piece.pieceColor))){
                    pieces[piece.getPieceVector().add(x).getX()][piece.getPieceVector().add(x).getY()].setBorders();
                }
            }
        }
    }
    public static void bishopMove(bishop piece, prebuildPiece[][] pieces){
        diagonalMove(piece, pieces);
    }
    private static void diagonalMove(prebuildChessPiece piece, prebuildPiece[][] pieces){
        for(chessVector x:possibleMovesBishop){
            int i = 1;
            while(checkBoardBorders(piece.getPieceVector().add(new chessVector(x.getX()*i, x.getY()*i)))){
                if(emptySquare(piece.getPieceVector().add(new chessVector(x.getX()*i, x.getY()*i)), pieces,piece.pieceColor)){
                    pieces[piece.getPieceVector().add(new chessVector(x.getX()*i, x.getY()*i)).getX()]
                            [piece.getPieceVector().add(new chessVector(x.getX()*i, x.getY()*i)).getY()].setBorders();
                }else{
                    if(enemy(piece.getPieceVector().add(new chessVector(x.getX()*i, x.getY()*i)), pieces,piece.pieceColor)){
                        ((prebuildChessPiece)pieces[piece.getPieceVector().add(new chessVector(x.getX()*i, x.getY()*i)).getX()]
                                [piece.getPieceVector().add(new chessVector(x.getX()*i, x.getY()*i)).getY()]).killBorders();
                    }
                    break;
                }
                ++i;
            }
        }
    }
    public static void queenMove(queen piece, prebuildPiece[][] pieces){
        linearMove(piece, pieces);
        diagonalMove(piece, pieces);
    }
    public static void kingMove(king piece, prebuildPiece[][] pieces){
        for(int x = -1; x<2; ++x){
            for(int y = -1; y<2; ++y){
                if(checkBoardBorders(piece.getPieceVector().add(new chessVector(x, y)))) {
                    if (emptySquare(piece.getPieceVector().add(new chessVector(x, y)), pieces, piece.pieceColor)) {
                        if(!checkCheckWithForwardMove(pieces, piece.getPieceVector(), piece.getPieceVector().add(new chessVector(x, y)), piece.pieceColor))
                            pieces[piece.getPieceVector().add(new chessVector(x, y)).getX()]
                                [piece.getPieceVector().add(new chessVector(x, y)).getY()].setBorders();
                    } else if (enemy(piece.getPieceVector().add(new chessVector(x, y)), pieces, piece.pieceColor)) {
                        if(!checkCheckWithForwardMove(pieces, piece.getPieceVector(), piece.getPieceVector().add(new chessVector(x, y)), piece.pieceColor))
                            ((prebuildChessPiece) pieces[piece.getPieceVector().add(new chessVector(x, y)).getX()][piece.getPieceVector()
                                .add(new chessVector(x, y)).getY()]).killBorders();
                    }
                }
            }
        }
    }
    public static boolean checkCheckWithForwardMove(prebuildPiece[][] pieces, chessVector pieceCoordinates,chessVector pieceDestiny, color kingColor){
        prebuildPiece[][] copyPieces = copyChessBoard(pieces);
        copyPieces[pieceDestiny.getX()][pieceDestiny.getY()] = copyPieces[pieceCoordinates.getX()][pieceCoordinates.getY()];
        copyPieces[pieceDestiny.getX()][pieceDestiny.getY()].getPieceVector().setX(pieceDestiny.getX());
        copyPieces[pieceDestiny.getX()][pieceDestiny.getY()].getPieceVector().setY(pieceDestiny.getY());
        copyPieces[pieceCoordinates.getX()][pieceCoordinates.getY()] = new emptyPiece(new chessVector(pieceCoordinates.getX(), pieceCoordinates.getY()));
        return check(kingColor, copyPieces);
    }
    public static boolean check(color kingColor ,prebuildPiece[][] pieces){
        prebuildChessPiece forMoment = focusPiece;
        for(int x = 0; x<8; ++x) {
            for (int y = 0; y<8; ++y) {
                if (pieces[x][y].pieceColor != kingColor && pieces[x][y].pieceColor != color.NONE && !(pieces[x][y] instanceof king)) {
                    checkInstanceOf((prebuildChessPiece) pieces[x][y], pieces);
                }
            }
        }
        focusPiece = forMoment;
        chessVector king = findKing(pieces, kingColor);

        color enemyKingColor = color.WHITE;
        if(kingColor == color.WHITE){
            enemyKingColor = color.BLACK;
        }

        chessVector enemyKing = findKing(pieces, enemyKingColor);
        int x = enemyKing.getX() - king.getX();
        int y = enemyKing.getY() - king.getY();
        if(x>=-1 && x<=1){
            if(y>=-1 && y<=1){
                return true;
            }
        }
        if(pieces[king.getX()][king.getY()].isOpaque()){
            return true;
        }
        return false;
    }
    public static boolean pat(prebuildPiece[][] pieces, color kingColor){
        for(int x = 0; x<8; ++x){
            for(int y = 0; y<8; ++y){
                if(pieces[x][y].pieceColor == kingColor) {
                    if (pieces[x][y] instanceof pawn) {
                        chessVector pawnVector = pieces[x][y].getPieceVector();
                        int yInside;
                        if(pieces[x][y].pieceColor == color.WHITE){
                            yInside = 1;
                        }else{
                            yInside = -1;
                        }
                        if(checkBoardBorders(new chessVector(pawnVector.getX() + 1, pawnVector.getY() + yInside))) {
                            if (enemy(new chessVector(pawnVector.getX() + 1, pawnVector.getY() + yInside), pieces, pieces[x][y].pieceColor)) {
                                if(!checkCheckWithForwardMove(pieces, pawnVector, pawnVector.add(new chessVector(1, yInside)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }
                        }
                        if(checkBoardBorders(new chessVector(pawnVector.getX()-1,pawnVector.getY()+yInside))) {
                            if (enemy(new chessVector(pawnVector.getX() - 1, pawnVector.getY() + yInside), pieces, pieces[x][y].pieceColor)) {
                                if(!checkCheckWithForwardMove(pieces, pawnVector, pawnVector.add(new chessVector(-1, yInside)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }
                        }
                        if(checkBoardBorders(new chessVector(pawnVector.getX(), pawnVector.getY() + yInside))) {
                            if (emptySquare(new chessVector(pawnVector.getX(), pawnVector.getY() + yInside), pieces, pieces[x][y].pieceColor)) {
                                if(!checkCheckWithForwardMove(pieces, pawnVector, pawnVector.add(new chessVector(0, yInside)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }
                        }
                        if((pawnVector.getY() == 1 || pawnVector.getY() == 6) && checkBoardBorders(new chessVector(pawnVector.getX(), pawnVector.getY() + 2 * yInside))) {
                            if (emptySquare(new chessVector(pawnVector.getX(), pawnVector.getY() + 2 * yInside), pieces, pieces[x][y].pieceColor)) {
                                if(!checkCheckWithForwardMove(pieces, pawnVector, pawnVector.add(new chessVector(0, 2*yInside)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }
                        }
                    } else if (pieces[x][y] instanceof rock) {
                        chessVector pieceVector = pieces[x][y].getPieceVector();
                        for(int i = pieceVector.getX()+1; i<8; ++i){
                            if(emptySquare(new chessVector(i, pieceVector.getY()), pieces, pieces[x][y].pieceColor)){
                                if(!(checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(i, 0)), pieces[x][y].pieceColor))){
                                    return false;
                                }
                            }else{
                                if(enemy(new chessVector(i, pieceVector.getY()), pieces, pieces[x][y].pieceColor)){
                                    if(!(checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(i, 0)), pieces[x][y].pieceColor))){
                                        return false;
                                    }
                                }
                                break;
                            }
                        }
                        for(int i = pieceVector.getX()-1; i>=0; --i){
                            if(emptySquare(new chessVector(i, pieceVector.getY()), pieces, pieces[x][y].pieceColor)){
                                if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(i, 0)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }else{
                                if(enemy(new chessVector(i, pieceVector.getY()), pieces, pieces[x][y].pieceColor)){
                                    if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(i, 0)), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }
                                break;
                            }
                        }
                        for(int i = pieceVector.getY()+1; i<8; ++i){
                            if(emptySquare(new chessVector(pieceVector.getX(), i), pieces, pieces[x][y].pieceColor)){
                                if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(0, i)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }else{
                                if(enemy(new chessVector(pieceVector.getX(), i), pieces, pieces[x][y].pieceColor)){
                                    if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(0, i)), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }
                                break;
                            }
                        }
                        for(int i = pieceVector.getY()-1; i>=0; --i){
                            if(emptySquare(new chessVector(pieceVector.getX(), i), pieces, pieces[x][y].pieceColor)){
                                if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(0, i)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }else{
                                if(enemy(new chessVector(pieceVector.getX(), i), pieces, pieces[x][y].pieceColor)){
                                    if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(0, i)), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }
                                break;
                            }
                        }
                    } else if (pieces[x][y] instanceof knight) {
                        for(chessVector i:possibleMovesKnight){
                            if(checkBoardBorders(pieces[x][y].getPieceVector().add(i))){
                                if(enemy(pieces[x][y].getPieceVector().add(i), pieces, pieces[x][y].pieceColor)){
                                    if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().add(i), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }else if(!(sameLikeAttacking(pieces[x][y].getPieceVector().add(i), pieces, pieces[x][y].pieceColor))){
                                    if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().add(i), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }
                            }
                        }
                    } else if (pieces[x][y] instanceof bishop) {
                        for(chessVector Moves:possibleMovesBishop){
                            int i = 1;
                            while(checkBoardBorders(pieces[x][y].getPieceVector().add(new chessVector(Moves.getX()*i, Moves.getY()*i)))){
                                if(emptySquare(pieces[x][y].getPieceVector().add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces,pieces[x][y].pieceColor)){
                                    if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().
                                            add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }else{
                                    if(enemy(pieces[x][y].getPieceVector().add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces,pieces[x][y].pieceColor)){
                                        if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().
                                                add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces[x][y].pieceColor)){
                                            return false;
                                        }
                                    }
                                    break;
                                }
                                ++i;
                            }
                        }
                    } else if (pieces[x][y] instanceof queen) {
                        chessVector pieceVector = pieces[x][y].getPieceVector();
                        for(int i = pieceVector.getX()+1; i<8; ++i){
                            if(emptySquare(new chessVector(i, pieceVector.getY()), pieces, pieces[x][y].pieceColor)){
                                if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(i, 0)), pieces[x][y].pieceColor)){
                                    return false;
                                }
                            }else{
                                if(enemy(new chessVector(i, pieceVector.getY()), pieces, pieces[x][y].pieceColor)){
                                    if(!checkCheckWithForwardMove(pieces, pieceVector, pieceVector.add(new chessVector(i, 0)), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }
                                break;
                            }
                        }

                        for(chessVector Moves:possibleMovesBishop){
                            int i = 1;
                            while(checkBoardBorders(pieces[x][y].getPieceVector().add(new chessVector(Moves.getX()*i, Moves.getY()*i)))){
                                if(emptySquare(pieces[x][y].getPieceVector().add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces,pieces[x][y].pieceColor)){
                                    if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().
                                            add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces[x][y].pieceColor)){
                                        return false;
                                    }
                                }else{
                                    if(enemy(pieces[x][y].getPieceVector().add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces,pieces[x][y].pieceColor)){
                                        if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().
                                                add(new chessVector(Moves.getX()*i, Moves.getY()*i)), pieces[x][y].pieceColor)){
                                            return false;
                                        }
                                    }
                                    break;
                                }
                                ++i;
                            }
                        }
                    } else if (pieces[x][y] instanceof king) {
                        for(int xx = -1; xx<2; ++xx){
                            for(int yy = -1; yy<2; ++yy){
                                if(checkBoardBorders(pieces[x][y].getPieceVector().add(new chessVector(xx, yy)))) {
                                    if (emptySquare(pieces[x][y].getPieceVector().add(new chessVector(xx, yy)), pieces, pieces[x][y].pieceColor)) {
                                        if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().add(new chessVector(xx, yy)), pieces[x][y].pieceColor)){
                                            return false;
                                        }
                                    } else if (enemy(pieces[x][y].getPieceVector().add(new chessVector(xx, yy)), pieces, pieces[x][y].pieceColor)) {
                                        if(!checkCheckWithForwardMove(pieces, pieces[x][y].getPieceVector(), pieces[x][y].getPieceVector().add(new chessVector(xx, yy)), pieces[x][y].pieceColor)){
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
    public static prebuildPiece[][] copyChessBoard(prebuildPiece[][] pieces){
        prebuildPiece[][] piecesCopy = new prebuildPiece[8][8];
        for(int x = 0; x<8; ++x){
            for(int y = 0; y<8; ++y){
                piecesCopy[x][y] = pieces[x][y].deepClone();
            }
        }
        return piecesCopy;
    }
    public static chessVector findKing(prebuildPiece[][] pieces, color kingColor){
        chessVector kingVector = null;
        outer:
        for(int x = 0; x<8; ++x){
            for(int y = 0; y<8; ++y){
                if(pieces[x][y] instanceof king && ((king) pieces[x][y]).pieceColor == kingColor){
                    kingVector = new chessVector(x, y);
                    break outer;
                }
            }
        }
        return kingVector;
    }
    private static boolean sameLikeAttacking(chessVector square, prebuildPiece[][] pieces, color pieceColor){
        return pieces[square.getX()][square.getY()].pieceColor == pieceColor;
    }
    private static boolean enemy(chessVector square, prebuildPiece[][] pieces, color pieceColor){
        if(pieceColor == color.WHITE){
            return pieces[square.getX()][square.getY()].pieceColor == color.BLACK;
        }else if(pieceColor == color.BLACK){
            return pieces[square.getX()][square.getY()].pieceColor == color.WHITE;
        }
        return false;
    }
    private static boolean emptySquare(chessVector square, prebuildPiece[][] pieces, color pieceColor){
        return !(sameLikeAttacking(square, pieces, pieceColor) || enemy(square, pieces, pieceColor));
    }
    private static boolean checkBoardBorders(chessVector square){
        if(square.getX() < 0 || square.getX() > 7){
            return false;
        }
        if(square.getY() < 0 || square.getY() > 7){
            return false;
        }
        return true;
    }
}
