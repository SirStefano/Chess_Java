package pieces;

import pieces.components.chessVector;
import pieces.components.color;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class prebuildChessPiece extends prebuildPiece{
    protected BufferedImage image;
    protected char letter;
    public prebuildChessPiece(chessVector _pieceVector , String chessName, color _Color){
        super(_pieceVector, _Color);
        String filePath = "../../chess_pieces/";
        if(pieceColor == color.WHITE){
            filePath += "white";
        }else {
            filePath += "black";
        }
        filePath += ("/" + chessName + ".png");
        loadImage(filePath);
        setIcon(new ImageIcon(image));
    }
    private void loadImage(String filePath){
        try {
            image = ImageIO.read(getClass().getResource(filePath));
        } catch (Exception ex) {
            System.out.println(ex);
            image = null;
        }
    }

    public void setBorders(){
        setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3, true));
        setBorderPainted(true);
    }
    public void killBorders(){
        setBorder(BorderFactory.createLineBorder(Color.RED, 5, true));
        setBorderPainted(true);
    }
    public abstract prebuildChessPiece deepClone();
}
