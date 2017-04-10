/**
 * Created by marci on 2017.03.17..
 */
package bem7trainsim;
public class Locomotive {
    private Train train;

    protected Rail now;
    private Rail before;

    public Locomotive(Train train, Rail now) throws CollisionException {
        this.now = now;
        this.train = train;
        now.arrive(train);
        before = null;
    }

    public Rail move() throws CollisionException {
        Rail next = null;
        if(now != null) next = now.next(before);
        before = now;
        now = next;
        if(next != null) next.arrive(train);
        return before;
    }

    public String getDrawData() {
        return "AV<>"; //TODO: how?
    }
}
