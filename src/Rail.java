import java.util.Collection;

/**
 * Created by marci on 2017.03.17..
 */
public abstract class Rail extends Field {
    private Train train;
    protected Collection<Rail> links;

    public void addRail(Rail rail) {
        links.add(rail);
    }

    public abstract Rail next(Rail from);

    public void leave() {}

    public void arrive(Train train) {}
}
