package bem7trainsim;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.18..
 */
public class CrossRail extends Rail {
    private List<Rail> crossLinks;

    public CrossRail() {
        super();
        this.crossLinks = new ArrayList<>();
        Main.objectCreated(this);
    }

    @Override
    public Rail next(Rail from) {
        if(links.contains(from)) {
            if(links.indexOf(from) == 0) return links.get(1);
            else return links.get(0);
        } else {
            if(crossLinks.indexOf(from) == 0) return crossLinks.get(1);
            else return crossLinks.get(0);
        }
    }

    public void addLinkToCross(Rail rail) {
        crossLinks.add(rail);
        System.out.println("KAPCSOLODOTT: "+Main.identityToString(this)+"  ->  "+Main.identityToString(rail)+" KERESZTBEN");
    }
}
