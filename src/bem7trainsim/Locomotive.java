package bem7trainsim;
/**
 * Created by marci on 2017.03.17.
 * Represents the locomotive, which is the first object of the train
 */
public class Locomotive extends Coach {
    /**
     * The table on which the Locomotive moves
     */
    private Table table;

    /**
     * The train pulled by the locomotive
     */
    private Train train;

    /**
     * The rail where the locomotive is going now
     */
    protected Rail now;

    /**
     * The rail where the locomotive was before
     */
    private Rail before;

    /**
     * Creates a locomotive with a starting position
     * @param train The train to be pulled by the locomotive
     * @param now The rail where it starts
     * @throws CollisionException Thrown if the starting rail is not empty
     */
    public Locomotive(Train train, Rail now, Table table) throws CollisionException {
        this.now = now;
        this.train = train;
        this.table = table;
        now.arrive(this);
        updateDirection();
        before = null;
    }

    /**
     * Gets the next rail from the rail where the locomotive is
     * @return The rail which the locomotive was moved from.
     * @throws CollisionException Thrown if the new rail is not empty
     * @throws TableLeftException Thrown if a not empty train left the table
     */
    public Rail move() throws CollisionException, TableLeftException {
        Rail next = null;
        // gets the next rail
        if(now != null) next = now.next(before);
        if(next == null && !train.isEmpty()) throw new TableLeftException("Gameover! A not empty train left the table.");
        // move to the next rail
        before = now;
        now = next;
        if(now != null) {
            now.arrive(train);
            now.arrive(this);
        }
        return before;
    }

    /**
     * Updates the direction of the locomotive
     * @return the updated direction of the locomotive
     */
    public Table.Direction updateDirection() {
        Table.Direction newDirection = null;
        if(now != null) newDirection = table.getDirection(now, now.next(before));
        if(newDirection != null) direction = newDirection;
        return direction;
    }

    /**
     * @return Returns the character drawn to the terminal when rendering the table
     */
    public String getDrawData() {
        return "L";
    }
}
