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
        Main.objectCreated(this);
    }

    public void addLink(Rail rail) {
        links.add(rail);
        System.out.println("KAPCSOLODOTT: "+Main.identityToString(this)+"  ->  "+Main.identityToString(rail));
    }

    public abstract Rail next(Rail from);

    public void leave() {
        System.out.println("ELHAGYTA: "+Main.identityToString(this)+" VONAT "+Main.identityToString(train));
        this.train = null;
    }

    public void arrive(Train train) throws CollisionException {
        if(train == null) return;
        if(this.train != null) {
            throw new CollisionException(train, this);
        }

        this.train = train;
        System.out.println("MEGERKEZETT: "+Main.identityToString(this)+"  <-  "+Main.identityToString(train));
    }
}
