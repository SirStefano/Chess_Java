package partsOfWindow;

import partsOfWindow.possibilitiesForMainArea.chessBoard;
import partsOfWindow.possibilitiesForMainArea.endGameMenu;
import partsOfWindow.possibilitiesForMainArea.mainMenu;

import javax.swing.*;
import java.awt.*;

public class mainArea extends JPanel {
    private chessBoard mainBoard;
    private mainMenu menu;
    private endGameMenu endGame;
    public mainArea(){
        super(null);
    }
    public void initChessBoard(){
        clear();
        mainBoard = new chessBoard(this);
        mainBoard.setBounds((int) (getSize().getWidth()/2-542/2),
                20, 662,542);
        add(mainBoard);
    }
    public void initMainManu(){
        clear();
        menu = new mainMenu(this);
        menu.setBounds((int) (getSize().getWidth()/2-180/2),
                20, 180,800);
        add(menu);
    }
    public void initEndGameMenu(String title){
        clear();
        endGame = new endGameMenu(this, title);
        endGame.setBounds((int) (getSize().getWidth()/2-180/2),
                20, 180,400);
        add(endGame);
    }
    private void clear(){
        removeAll();
        revalidate();
        repaint();
    }
}
