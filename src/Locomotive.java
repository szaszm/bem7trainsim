/**
 * Created by marci on 2017.03.17..
 */
public class Locomotive {
    private Train train;

    protected Rail now;
    private Rail before;

    public Locomotive(Train train, Rail now) {
        this.now = now;
        before = null;
    }

    public Rail move() {
        Rail next = now.next(before);
        before = now;
        now = next;
        return before;
    }
}
