package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class SimpleRail extends Rail {

    public SimpleRail() {
        super();
    }

    @Override
    public Rail next(Rail from) {
        if(links.indexOf(from) == 0) {
            return links.size() > 1 ? links.get(1) : null;
        } else {
            return links.size() > 0 ? links.get(0) : null;
        }
    }

    @Override
    String getDrawData() {
        return "═║"; //TODO: needs data from ctor
    }
}
