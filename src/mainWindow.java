import modifiedComponents.panelWithBackground;
import partsOfWindow.chessBoard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/layout/none.html
//540x540 chess board

public class mainWindow extends JFrame{
    public static void main(String[] args){
        mainWindow window = new mainWindow("Chess");
        window.init();
        window.setVisible(true);
    }

    mainWindow(String title){
        super(title);
    }
    private void init(){
        setSize(1280,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //do wywalnia
        JTextField t1 = new JTextField("południe");
        JButton cb1 = new JButton("wschód");
        JButton cb2 = new JButton("północ");
        JButton cb3 = new JButton("zachód");
        //do tego miejsca

        //próbny poniżej
        JPanel jpp = new JPanel(null);

        chessBoard mainBoard = new chessBoard();

        jpp.add(mainBoard); //jp ma być środkowane (: i jakaś grafika w tle


        JButton cb4 = new JButton();
        //
        cb4.setOpaque(false);
        cb4.setContentAreaFilled(false);
        cb4.setBorderPainted(false);
        //komendy do przezroczystości przycisku


        //do wywalenia
        add(t1, BorderLayout.SOUTH);
        add(cb1, BorderLayout.EAST);

        add(cb2, BorderLayout.NORTH);
        add(cb3, BorderLayout.WEST);
        //do tego miejsca
        add(jpp, BorderLayout.CENTER);

        try {
            Image img = ImageIO.read(getClass().getResource("./chess_pieces/black/King.png"));
            cb4.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
