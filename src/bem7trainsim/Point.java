package bem7trainsim;

/**
 * Created by Kristóf on 2017. 05. 07..
 */
public class Point {

    private int x,y;

    public Point(int _x, int _y){
        x=_x;
        y=_y;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
