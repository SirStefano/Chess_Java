package partsOfWindow.partsOfChessBoard;

import javax.swing.*;
import java.awt.*;

public class panelsWithCoordinates extends JPanel {
    JLabel[] coordinates = new JLabel[8];
    public panelsWithCoordinates(char begin){
        super(new FlowLayout(FlowLayout.CENTER, 40, 19));
        for(int i = 0; i<8; ++i){
            coordinates[i] = new JLabel(Character.toString((int)begin+i), SwingConstants.CENTER);
            coordinates[i].setFont(new Font("Serif", Font.PLAIN, 30));
            add(coordinates[i]);
        }
    }
    public void rotate(){
        String s;
        for(int i = 0; i<4; ++i){
            s = coordinates[i].getText();
            coordinates[i].setText(coordinates[7-i].getText());
            coordinates[7-i].setText(s);
        }
    }
}
