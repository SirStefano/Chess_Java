package partsOfWindow.possibilitiesForMainArea.partsOfChessBoard.movesEngine;

import pieces.chessPieces.*;
import pieces.components.chessVector;
import pieces.prebuildChessPiece;
import pieces.components.color;

import javax.swing.*;

public class choosePromotionPiece extends JDialog {
    private prebuildChessPiece[] buttons = new prebuildChessPiece[4];
    private JLabel chooseText;
    public choosePromotionPiece(color _Color){
        setLayout(null);
        setSize(308, 200);
        buttons[0] = new queen(new chessVector(0, 0), _Color);
        buttons[1] = new rock(new chessVector(0, 0), _Color);
        buttons[2] = new bishop(new chessVector(0, 0), _Color);
        buttons[3] = new knight(new chessVector(0, 0), _Color);
        chooseText = new JLabel("Choose a piece for promotion:", SwingConstants.CENTER);
        chooseText.setBounds(10,10,270, 60);
        add(chooseText);
        for(int i = 0; i<4; ++i){
            buttons[i].setBounds((10+i*60+i*10), 80, 60, 60);
            add(buttons[i]);
        }
    }
    public prebuildChessPiece[] getButtons(){
        return buttons;
    }
}
