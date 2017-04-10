package bem7trainsim;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.17..
 */
public abstract class Rail extends Field {
    /**
     * The train of the Rail
     */
    protected Train train;
    /**
     * The coahc of the rail
     */
    protected Coach coach;
    /**
     * The rails where this rail is connected
     */
    protected List<Rail> links;

    /**
     * Creates a new rail.
     */
    protected Rail() {
        links = new ArrayList<>();
    }

    /**
     * Add a link to other rails.
     * @param rail The rail to link
     */
    public void addLink(Rail rail) {
        links.add(rail);
    }

    /**
     * Gets the next rail if the rail is given where the train comes from
     * @param from The rail where the train comes from
     * @return The next rail
     */
    public abstract Rail next(Rail from);

    /**
     * Handles when the train leaving this rail
     */
    public void leave() {
        this.train = null;
        this.coach = null;
    }

    /**
     * Handles when a train arriving to this rail
     * @param train The train which is arrived
     * @throws CollisionException Thrown if the rail has a train
     */
    public void arrive(Train train) throws CollisionException {
        if(train == null) return;
        if(this.train != null) {
            throw new CollisionException(train, this);
        }
        this.train = train;
    }

    /**
     * Handles when a coach arriving to this rail
     * @param coach The coach which is arrived
     */
    public void arrive(Coach coach) {
        this.coach = coach;
    }
}
