import java.awt.*;

/**
 * Created by marci on 2017.03.17..
 */
public class Wagon {
    protected Rail now;
    private Train train;
    private Color color;
    private boolean _isEmpty;

    public Wagon(Train train, Color color) {
        this.color = color;
        this.train = train;
        _isEmpty = false;
    }

    public void empty(Color color) {
        if(color == this.color) {
            _isEmpty = true;
        }
    }

    public boolean isEmpty() {
        return _isEmpty;
    }

    public Rail move(Rail to) {
        Rail old = now;

        now = to;

        return old;
    }
}
