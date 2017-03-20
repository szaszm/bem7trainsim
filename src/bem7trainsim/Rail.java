package bem7trainsim;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.17..
 */
public abstract class Rail extends Field {
    protected Train train;
    protected List<Rail> links;

    protected Rail() {
        links = new ArrayList<>();
    }

    public void addLink(Rail rail) {
        links.add(rail);
    }

    public abstract Rail next(Rail from);

    public void leave() {
        this.train = null;
    }

    public void arrive(Train train) throws TrainCollisionException {
        if(train == null) return;
        if(this.train != null) {
            throw new TrainCollisionException(train, this);
        }

        this.train = train;
    }
}
