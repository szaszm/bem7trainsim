package bem7trainsim;
import java.awt.Color;

/**
 * Created by marci on 2017.03.17..
 */
public class Wagon {
    protected Rail now;
    private Color color;

    public Wagon() {
        this(Color.GRAY);
    }

    public Wagon(Color color) {
        this.color = color;
    }

    public void empty(Color color) {
        if(color == this.color) {
            color = Color.GRAY;
            System.out.println("URULT: "+ Main.identityToString(this));
        }
    }

    public boolean isEmpty() {
        return color == Color.GRAY;
    }

    public Rail move(Rail to) {
        Rail old = now;

        now = to;

        return old;
    }
}
