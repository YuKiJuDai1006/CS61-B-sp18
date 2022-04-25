package byog.Core;

public class Position {
    private int xPos;
    private int yPos;

    public Position(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getX(){
        return xPos;
    }

    public int getY(){
        return yPos;
    }
}
