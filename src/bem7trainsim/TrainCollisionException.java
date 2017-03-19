package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class TrainCollisionException extends Exception {
    private Train train;
    private Rail rail;

    public TrainCollisionException(Train train, Rail rail) {
        super("Utkozott ket vonat");
        this.train = train;
        this.rail = rail;
    }
}
