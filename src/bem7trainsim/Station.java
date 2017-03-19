package bem7trainsim;
import java.awt.*;

/**
 * Created by marci on 2017.03.18..
 */
public class Station extends SimpleRail {
    private Color color;

    public Station(Color color) {
        super();
        this.color = color;
    }

    public void arrive(Train train) {
        train.empty(color);
    }
}
