import java.awt.Color;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marci on 2017.03.17..
 */
public class Train {
    private Locomotive locomotive;
    private List<Wagon> wagons;

    public Train(Rail start, List<Wagon> wagons) {
        this.wagons = wagons;
        locomotive = new Locomotive(this, start);
    }

    public void empty(Color color) {
        Iterator<Wagon> it = wagons.iterator();
        Wagon wagon = null;
        while (it.hasNext() && (wagon = it.next()).isEmpty());
        if(wagon == null) return;
        wagon.empty(color);
        // TODO: csak amig ugyanaz a szin
        while((wagon = it.next()) != null) {
            wagon.empty(color);
        }
    }

    public void move() throws TrainCollisionException {
        Rail next = locomotive.move();
        for(Wagon wagon: wagons) {
            next = wagon.move(next);
        }
        if(next != null) next.leave();
    }
}
