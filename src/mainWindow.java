import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

//https://www.w3docs.com/snippets/java/how-to-add-an-image-to-a-jpanel.html
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
    void init(){
        setSize(1280,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //do wywalnia
        JTextField t1 = new JTextField("południe");
        JButton cb1 = new JButton("wschód");
        JButton cb2 = new JButton("północ");
        JButton cb3 = new JButton("zachód");
        //do tego miejsca
        JPanel jp = new JPanel(new GridLayout(5,5));
        JButton cb4 = new JButton();

        //do wywalenia
        add(t1, BorderLayout.SOUTH);
        add(cb1, BorderLayout.EAST);
        add(cb2, BorderLayout.NORTH);
        add(cb3, BorderLayout.WEST);
        //do tego miejsca
        add(cb4, BorderLayout.CENTER);

        try {
            Image img = ImageIO.read(getClass().getResource("./chess_pieces/black/King.png"));
            cb4.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
