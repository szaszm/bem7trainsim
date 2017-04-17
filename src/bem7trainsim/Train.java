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

    // sajnos kell, hogy mikor vizsgálja a kirajzolandó karaktert, akkor ezt ne tegye, különben alagútban elszáll
    //ennek értékét mindig a Tunnel enter() és leave() függvényei állítják be
    public boolean onSurface;

    public Train(Rail start, Table table) throws CollisionException {
        // creates train with empty list
        this(start, table, new ArrayList<>());
        onSurface = true;
    }

    public Train(Rail start, Table table, List<Wagon> wagons) throws CollisionException {
        this.wagons = wagons;
        // creates a locomotive as every Train needs one in the front
        locomotive = new Locomotive(this, start, table);
        onSurface = true;
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

    public void move() throws CollisionException, TableLeftException {
        // moves the locomotive to the next rail
        Rail next = locomotive.move();
        Table.Direction nextDirection = Table.Direction.Up;
        if(onSurface){
            nextDirection = locomotive.updateDirection();
        }

        // moves each wagon to the rail previous wagon was on
        for(Wagon wagon: wagons) {
            next = wagon.move(next);
            nextDirection = wagon.updateDirection(nextDirection);
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
    public boolean isEmpty(){
        boolean empty = true;
        for(Wagon wagon: wagons) {
            if(!wagon.isEmpty())
                empty = false;
        }
        return empty;
    }
}
