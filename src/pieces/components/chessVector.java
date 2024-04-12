package pieces.components;

public final class chessVector {
    private int x;
    private int y;
    public chessVector(int _x, int _y){
        x = _x;
        y = _y;
    }
    public chessVector add(chessVector vector1){
        return new chessVector(vector1.getX()+ x, vector1.getY()+ y);
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int _x) {
        x = _x;
    }

    public void setY(int _y) {
        y = _y;
    }
    public String chessNotation(){
        return Integer.toString(((char)(97+x)))+(y+1);
    }
    public chessVector deepClone(){
        return new chessVector(x, y);
    }
}
