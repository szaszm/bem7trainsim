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
        this.color = color;
        empty = false;
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
        now.arrive(this);

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
                return "i";
            }
            else if (color.equals(Color.YELLOW)) {
                return "á";
            }
            else if (color.equals(Color.GREEN)) {
                return "ö";
            }
            return "é";
        }
        if (color.equals(Color.RED)) {
            return "I";
        }
        else if (color.equals(Color.YELLOW)) {
            return "Á";
        }
        else if (color.equals(Color.GREEN)) {
            return "Ö";
        }
        return "É";
    }
}
