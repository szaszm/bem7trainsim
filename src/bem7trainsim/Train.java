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

    /**
     * Represents the trains position. Surface or under surface.
     */
    public boolean onSurface;

    /**
     * Creates train
     * @param start Starting field
     * @param table
     * @throws CollisionException thrown if trains collided
     */
    public Train(Rail start, Table table) throws CollisionException {
        // creates train with empty list
        this(start, table, new ArrayList<>());
        onSurface = true;
    }

    /**
     * Creates train
     * @param start Starting field
     * @param table
     * @param wagons list of wagons
     * @throws CollisionException thrown if trains collided
     */
    public Train(Rail start, Table table, List<Wagon> wagons) throws CollisionException {
        this.wagons = wagons;
        // creates a locomotive as every Train needs one in the front
        locomotive = new Locomotive(this, start, table);
        onSurface = true;
        start.arrive(this);
    }

    /**
     * empties the wagons determined by the color
     * @param color
     */
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


    /**
     * Moves the train
     * @throws CollisionException thrown when trains collide
     * @throws TableLeftException thrown when a not empty train leaves the table
     */
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

    /**
     * Gets the passangers on the train
     * @param color
     * @return true if the getting on is succesful
     */
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

    /**
     * @return true if the train is empty
     */
    public boolean isEmpty(){
        boolean empty = true;
        for(Wagon wagon: wagons) {
            if(!wagon.isEmpty())
                empty = false;
        }
        return empty;
    }
}
