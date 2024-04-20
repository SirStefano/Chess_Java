package partsOfWindow.possibilitiesForMainArea;

import partsOfWindow.mainArea;
import partsOfWindow.possibilitiesForMainArea.partsOfChessBoard.movesEngine.mainEngine;
import pieces.prebuildChessPiece;
import pieces.prebuildPiece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class mainMenu extends JPanel implements MouseListener {
    private final mainArea precursor;
    private JButton[] buttons = new JButton[3];
    public mainMenu(mainArea area){
        super(new FlowLayout());
        precursor = area;
        init();
    }
    private void init(){
        add(new JLabel("Welcome to chess game"));
        add(new JLabel("Choose mode to play"));
        buttons[0] = new JButton("local with your buddy");
        buttons[1] = new JButton("versus computer");
        buttons[2] = new JButton("online");
        for(JButton x:buttons){
            x.addMouseListener(this);
            add(x);
        }
        setBounds(0, 0, 180, 800);
    }

    public void mouseEntered(MouseEvent evt) {
    }
    public void mouseExited(MouseEvent evt) {
    }
    public void mouseClicked(MouseEvent evt) {
        JButton button = (JButton) evt.getComponent();
        if(button == buttons[0]){
            precursor.initChessBoard();
        }else if(button == buttons[1]){

        }else if(button == buttons[2]){

        }
    }
    public void mousePressed(MouseEvent evt) {
    }
    public void mouseReleased(MouseEvent evt) {
    }
}
