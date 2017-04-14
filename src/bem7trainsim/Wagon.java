package bem7trainsim;
import java.awt.Color;

/**
 * Created by marci on 2017.03.17..
 */
public class Wagon extends Coach {
    protected Rail now;
    private Color color;
    private boolean empty;

    public Wagon(Color color) {
        this(color, false);
    }

    public Wagon(Color color, boolean empty) {
        this.color = color;
        this.empty = empty;
    }

    public void empty(Color color) {
        if(color == this.color) {
            empty = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public Rail move(Rail to) {
        Rail old = now;

        now = to;
        if(now != null) now.arrive(this);

        return old;
    }

    public boolean getOn(Color color) {
        if (empty && color.equals(this.color)) {
            empty = false;
            return true;
        }
        return false;
    }

    public String getDrawData() {
        if (empty) {
            if (color.equals(Color.RED)) {
                return "p";
            }
            else if (color.equals(Color.YELLOW)) {
                return "s";
            }
            else if (color.equals(Color.GREEN)) {
                return "z";
            }
            return "k";
        }
        if (color.equals(Color.RED)) {
            return "P";
        }
        else if (color.equals(Color.YELLOW)) {
            return "S";
        }
        else if (color.equals(Color.GREEN)) {
            return "Z";
        }
        return "K";
    }
}
