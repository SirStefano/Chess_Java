package partsOfWindow.partsOfChessBoard.movesEngine;

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
    ,new chessVector(2,1),new chessVector(-1,2),new chessVector(-2,-1),new chessVector(1,2)};
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
    public static void makingMove(prebuildPiece piece, prebuildPiece[][] pieces){
        if(focusPiece instanceof pawn && (piece.getPieceVector().getY() == 7 || piece.getPieceVector().getY() == 0)){
            choosePromotionPiece chooseWindow = new choosePromotionPiece(focusPiece.pieceColor);
            buttonsHandler forWindow = new buttonsHandler(focusPiece, chooseWindow);
            for(int i = 0; i<4; ++i){
                chooseWindow.getButtons()[i].addMouseListener(forWindow);
            }
            chooseWindow.setLocationRelativeTo(focusPiece);
            chooseWindow.setModal(true);
            chooseWindow.setVisible(true);
            focusPiece = forWindow.getFocusPiece();
            if(focusPiece instanceof pawn){
                focusPiece = forWindow.changePiece(new queen(new chessVector(0,0),focusPiece.pieceColor));
            }
        }
        pieces[piece.getPieceVector().getX()][piece.getPieceVector().getY()] = focusPiece;
        pieces[focusPiece.getPieceVector().getX()][focusPiece.getPieceVector().getY()]
                = new emptyPiece(new chessVector(focusPiece.getPieceVector().getX(), focusPiece.getPieceVector().getY()));
        for(MouseListener x: focusPiece.getMouseListeners()){
            pieces[focusPiece.getPieceVector().getX()][focusPiece.getPieceVector().getY()].addMouseListener(x);
        }
        focusPiece.getPieceVector().setX(piece.getPieceVector().getX());
        focusPiece.getPieceVector().setY(piece.getPieceVector().getY());
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
                        if(!checkCheckWithForwardMove(pieces, piece.getPieceVector().add(new chessVector(x, y)), piece.pieceColor))
                            pieces[piece.getPieceVector().add(new chessVector(x, y)).getX()]
                                [piece.getPieceVector().add(new chessVector(x, y)).getY()].setBorders();
                    } else if (enemy(piece.getPieceVector().add(new chessVector(x, y)), pieces, piece.pieceColor)) {
                        if(!checkCheckWithForwardMove(pieces, piece.getPieceVector().add(new chessVector(x, y)), piece.pieceColor))
                            ((prebuildChessPiece) pieces[piece.getPieceVector().add(new chessVector(x, y)).getX()][piece.getPieceVector()
                                .add(new chessVector(x, y)).getY()]).killBorders();
                    }
                }
            }
        }
    }
    public static boolean checkCheckWithForwardMove(prebuildPiece[][] pieces, chessVector kingDestiny, color kingColor){
        prebuildPiece[][] copyPieces = copyChessBoard(pieces);
        chessVector kingVector = findKing(copyPieces, kingColor);
        copyPieces[kingDestiny.getX()][kingDestiny.getY()] = copyPieces[kingVector.getX()][kingVector.getY()];
        copyPieces[kingDestiny.getX()][kingDestiny.getY()].getPieceVector().setX(kingDestiny.getX());
        copyPieces[kingDestiny.getX()][kingDestiny.getY()].getPieceVector().setY(kingDestiny.getY());
        copyPieces[kingVector.getX()][kingVector.getY()] = new emptyPiece(new chessVector(kingVector.getX(), kingVector.getY()));
        return check(kingColor, copyPieces);
    }
    public static boolean check(color pieceColor ,prebuildPiece[][] pieces){
        prebuildChessPiece forMoment = focusPiece;
        for(int x = 0; x<8; ++x) {
            for (int y = 0; y<8; ++y) {
                if (pieces[x][y].pieceColor != pieceColor && pieces[x][y].pieceColor != color.NONE) {
                    checkInstanceOf((prebuildChessPiece) pieces[x][y], pieces);
                }
            }
        }
        focusPiece = forMoment;
        chessVector king = findKing(pieces, pieceColor);
        if(pieces[king.getX()][king.getY()].isOpaque()){
            return true;
        }
        return false;
    }
    public static boolean pat(){
        return true;
    }
    private static prebuildPiece[][] copyChessBoard(prebuildPiece[][] pieces){
        prebuildPiece[][] piecesCopy = new prebuildPiece[8][8];
        for(int x = 0; x<8; ++x){
            for(int y = 0; y<8; ++y){
                piecesCopy[x][y] = pieces[x][y].deepClone();
            }
        }
        return piecesCopy;
    }
    private static chessVector findKing(prebuildPiece[][] pieces, color kingColor){
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
    public static boolean checkmate(){
        return true;
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
