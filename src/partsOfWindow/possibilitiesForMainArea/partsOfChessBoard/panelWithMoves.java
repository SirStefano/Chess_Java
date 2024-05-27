package partsOfWindow.possibilitiesForMainArea.partsOfChessBoard;

import javax.swing.*;
import java.util.ArrayList;

public class panelWithMoves extends JPanel {
    private final ArrayList<String> whiteMoves;
    private final ArrayList<String> blackMoves;
    public panelWithMoves(){
        super(null);
        whiteMoves = new ArrayList<>();
        blackMoves = new ArrayList<>();
        init();
    }
    private void init(){
        JLabel white = new JLabel("White:");
        white.setBounds(10,10,60,20);
        add(white);
        JLabel black = new JLabel("Black:");
        black.setBounds(60,10,60,20);
        add(black);
    }
    public void newMove(){
        if(whiteMoves.size()>blackMoves.size()){
            JLabel move = new JLabel(whiteMoves.getLast());
            move.setBounds(10, whiteMoves.size()*30,60, 20);
            add(move);
        }else{
            JLabel move = new JLabel(blackMoves.getLast());
            move.setBounds(60, blackMoves.size()*30,60, 20);
            add(move);
        }
        System.out.println(whiteMoves.getLast());
    }
    public ArrayList<String> getWhiteMoves(){
        return whiteMoves;
    }
    public ArrayList<String> getBlackMoves(){
        return blackMoves;
    }
}
