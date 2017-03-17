import java.awt.*;
import java.util.Collection;

/**
 * Created by marci on 2017.03.17..
 */
public class Train {
    private Locomotive locomotive;
    private Collection<Wagon> wagons;

    public Train(Rail start, Collection<Wagon> wagons) {
        this.wagons = wagons;
        locomotive = new Locomotive(this, start);
    }

    public void empty(Color color) {
        // TODO
    }
}
