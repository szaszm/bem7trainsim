package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class CollisionException extends Exception {
    private Train train;
    private Rail rail;

    public CollisionException(Train train, Rail rail) {
        super("Utkozott ket vonat");
        this.train = train;
        this.rail = rail;
    }
}
