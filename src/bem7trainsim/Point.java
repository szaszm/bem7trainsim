package bem7trainsim;

/**
 * Represents a point on the table
 * Created by Kristóf on 2017. 05. 07..
 */
public class Point {

    /**
     * @var x The horizontal coordinate
     * @var y The vertical coordinate
     */
    private int x,y;

    /**
     * Point constructor
     * @param _x The horizontal coordinate
     * @param _y The vertical coordinate
     */
    public Point(int _x, int _y){
        x=_x;
        y=_y;
    }


    /**
     * Returns the horizontal coordinate
     * @return The horizontal coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the horizontal coordinate
     * @param x The new horizontal coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the vertical coordinate
     * @return The vertical coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the vertical coordinate
     * @param y The new vertical coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}
