package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class SimpleRail extends Rail {

    /**
     * The orientation of the rail
     */
    public enum Orientation {
        Horizontal, Vertical
    }

    /**
     * The orientation of the rail
     */
    private Orientation orientation;

    /**
     * Creates a simple rail with the given orientation.
     * @param orientation The orientation
     */
    public SimpleRail(Orientation orientation) {
        super();
        this.orientation = orientation;
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
    public String getDrawData() {
        if (coach != null)
            return coach.getDrawData();
        return (orientation == Orientation.Horizontal) ? "═" : "║";
    }
}
