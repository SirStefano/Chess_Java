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
        initMainManu();
    }
    public void initChessBoard(){
        clear();
        mainBoard = new chessBoard(this);
        add(mainBoard);
    }
    public void initMainManu(){
        clear();
        menu = new mainMenu(this);
        add(menu);
    }
    public void initEndGameMenu(String title){
        clear();
        endGame = new endGameMenu(this, title);
        add(endGame);
    }
    private void clear(){
        removeAll();
        revalidate();
        repaint();
    }
}
