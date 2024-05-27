import partsOfWindow.mainArea;
import partsOfWindow.possibilitiesForMainArea.mainMenu;
import pieces.components.color;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

//https://docs.oracle.com/javase%2Ftutorial%2Fuiswing%2F%2F/layout/none.html
//540x540 chess board

public class mainWindow extends JFrame{
    public static void main(String[] args){
        mainWindow window = new mainWindow("Chess");
        window.init();
        window.setVisible(true);
    }

    public void mat(){
        System.out.println("Jesteśmy w domu");
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
        mainArea jpp = new mainArea();



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
        setVisible(true);
        jpp.initMainManu();
    }
}
