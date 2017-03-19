/**
 * Created by marci on 2017.03.17..
 */
package bem7trainsim;
public class Locomotive {
    private Train train;

    protected Rail now;
    private Rail before;

    public Locomotive(Train train, Rail now) throws TrainCollisionException {
        this.now = now;
        this.train = train;
        now.arrive(train);
        before = null;
        Main.objectCreated(this);
    }

    public Rail move() throws TrainCollisionException {
        Rail next = null;
        if(now != null) next = now.next(before);
        before = now;
        now = next;
        if(next != null) next.arrive(train);
        return before;
    }
}
