package bem7trainsim;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a wagon.
 * Created by marci on 2017.03.17..
 */
public class Wagon extends Coach {

    /**
     * current position of the wagon
     */
    protected Rail now;

    /**
     * color of the wagon
     */
    private Color color;

    /**
     * true if the wagon is empty, else false
     */
    private boolean empty;

    /**
     * Creates a wagon with the given color
     * @param color The color of the wagon
     */
    public Wagon(Color color) {
        this(color, false);
    }

    /**
     * Creates a wagon with the given color
     * @param color The color of the wagon
     * @param empty True when the wagon is empty
     */
    public Wagon(Color color, boolean empty) {
        this.color = color;
        if(color.equals(Color.BLACK))
            this.empty = true;
        else this.empty = empty;
    }

    /**
     * Empties the wagon if the colors matching
     * @param color The color of the station
     */
    public void empty(Color color) {
        if(color == this.color) {
            empty = true;
        }
    }

    /**
     * @return true if the wagon is empty
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * Moving the wagon
     * @param to destination rail
     * @return previous rail
     */
    public Rail move(Rail to) {
        Rail old = now;

        now = to;
        if(now != null) now.arrive(this);

        return old;
    }

    /**
     * Putting passengers to the wagon
     * @param color The color of the station
     * @return true if the getting on was succesful
     */
    public boolean getOn(Color color) {
        if (empty && color.equals(this.color)) {
            empty = false;
            return true;
        }
        return false;
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    public List<String> getDrawData() {
        List<String> result = new ArrayList<>();
        if (empty) {
            if (color.equals(Color.RED)) {
                result.add("p");
                result.add("x");
            }
            else if (color.equals(Color.YELLOW)) {
                result.add("s");
                result.add("x");
            }
            else if (color.equals(Color.GREEN)) {
                result.add("z");
                result.add("x");
            }
            else if (color.equals(Color.BLUE)){
                result.add("k");
                result.add("x");
            }
            else result.add("F");
        }
        else if (color.equals(Color.RED)) {
            result.add("P");
        }
        else if (color.equals(Color.YELLOW)) {
            result.add("S");
        }
        else if (color.equals(Color.GREEN)) {
            result.add("Z");
        }
        else if (color.equals(Color.BLUE)) {
            result.add("K");
        }

        return result;
    }

    /**
     * updating the direction to the given value
     * @param direction The new direction
     * @return the previous direction
     */
    public Table.Direction updateDirection(Table.Direction direction) {
        Table.Direction oldDirection = this.direction;
        this.direction = direction;
        return oldDirection;
    }

    public Color getColor() {
        return color;
    }
}
