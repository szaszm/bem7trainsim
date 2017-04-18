package bem7trainsim;
/**
 * Created by marci on 2017.03.18.
 * Thrown when two trains collide
 */
public class CollisionException extends Exception {
    private Train train;
    private Rail rail;

    /**
     * @param train The train which crashes into the other object
     * @param rail The rail where the collision happens
     */
    public CollisionException(Train train, Rail rail) {
        super("Utkozott ket vonat");
        this.train = train;
        this.rail = rail;
    }
}
