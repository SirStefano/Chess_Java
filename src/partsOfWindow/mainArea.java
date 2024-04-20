package partsOfWindow;

import partsOfWindow.possibilitiesForMainArea.chessBoard;
import partsOfWindow.possibilitiesForMainArea.mainMenu;

import javax.swing.*;
import java.awt.*;

public class mainArea extends JPanel {
    private chessBoard mainBoard;
    private mainMenu menu;
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
    private void clear(){
        removeAll();
        revalidate();
        repaint();
    }
    public void dajGlos(){
        System.out.println("Jest cudownie");
    }
}
