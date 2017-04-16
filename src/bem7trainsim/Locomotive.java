/**
 * Created by marci on 2017.03.17..
 */
package bem7trainsim;
public class Locomotive extends Coach {
    /**
     * The train pulled by the locomotive
     */
    private Train train;

    /**
     * The rail where going now
     */
    protected Rail now;
    /**
     * The rail where the loco was before
     */
    private Rail before;

    /**
     * Creates a loco with a starting position
     * @param train The train to be pulled by the loco
     * @param now The rail where it starts
     * @throws CollisionException Thrown if the starting rail is not empty
     */
    public Locomotive(Train train, Rail now) throws CollisionException {
        this.now = now;
        this.train = train;
        now.arrive(this);
        before = null;
    }

    /**
     * Gets the next rail from the rail where the loco is
     * @return The rail moved from.
     * @throws CollisionException Thrown if the new rail is not empty
     */
    public Rail move() throws CollisionException {
        Rail next = null;
        // gets the next rail
        if(now != null) next = now.next(before);
        // move to the next rail
        before = now;
        now = next;
        if(now != null) {
            now.arrive(train);
            now.arrive(this);
        }
        return before;
    }

    public String getDrawData() {
        return "A"; //TODO: how? maybe ask the Table for the data.
    }
}
