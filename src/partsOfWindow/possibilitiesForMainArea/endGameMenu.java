package partsOfWindow.possibilitiesForMainArea;

import partsOfWindow.mainArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class endGameMenu extends JPanel implements MouseListener{
    private final mainArea precursor;
    JButton mainManu;
    JButton playAgain;
    public endGameMenu(mainArea area, String title) {
        setLayout(new FlowLayout());
        precursor = area;
        init(title);
    }
    private void init(String title){
        add(new JLabel(title));
        add(new JLabel("What next?"));
        mainManu = new JButton("Main Menu");
        playAgain = new JButton("Play again");
        mainManu.addMouseListener(this);
        playAgain.addMouseListener(this);
        add(mainManu);
        add(playAgain);
    }
    public void mouseEntered(MouseEvent evt) {
    }
    public void mouseExited(MouseEvent evt) {
    }
    public void mouseClicked(MouseEvent evt) {
        JButton button = (JButton) evt.getComponent();
        if(button == mainManu){
            precursor.initMainManu();
        }else if(button == playAgain){
            precursor.initChessBoard();
        }
    }
    public void mousePressed(MouseEvent evt) {
    }
    public void mouseReleased(MouseEvent evt) {
    }
}
