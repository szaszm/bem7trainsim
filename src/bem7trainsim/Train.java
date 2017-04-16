package bem7trainsim;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by marci on 2017.03.17..
 */
public class Train {
    private Locomotive locomotive;
    private List<Wagon> wagons;

    public Train(Rail start) throws CollisionException {
        // creates train with empty list
        this(start, new ArrayList<>());
    }

    public Train(Rail start, List<Wagon> wagons) throws CollisionException {
        this.wagons = wagons;
        // creates a locomotive as every Train needs one in the front
        locomotive = new Locomotive(this, start);
        start.arrive(this);
    }

    public void empty(Color color) {
        Iterator<Wagon> it = wagons.iterator();
        Wagon wagon = null;
        // searches for the first empty wagon
        while (it.hasNext() && (wagon = it.next()).isEmpty());
        if(wagon == null) return;
        wagon.empty(color);
        // tried to empty so if empty go on, else others cannot be emptied
        while(wagon.isEmpty() && it.hasNext() && (wagon = it.next()) != null) {
            wagon.empty(color);
        }
    }

    public void move() throws CollisionException {
        // moves the locomotive to the next rail
        Rail next = locomotive.move();
        // moves each wagon to the rail previous wagon was on
        for(Wagon wagon: wagons) {
            next = wagon.move(next);
        }
        // leaves the last rail the train moved from
        if(next != null) next.leave();
    }

    public boolean getOn(Color color) {
        // tries to put the color on each wagon
        for (Wagon wagon:
             wagons) {
            if (wagon.getOn(color)) {
                return true;
            }
        }
        // returns false if cannot
        return false;
    }
}
