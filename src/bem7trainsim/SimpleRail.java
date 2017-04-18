package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class SimpleRail extends Rail {

    /**
     * The orientation of the rail
     */
    public enum Orientation {
        HORIZONTAL, VERTICAL, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    }

    /**
     * The orientation of the rail
     */
    protected Orientation orientation;

    /**
     * Creates a simple rail with the given orientation.
     * @param orientation The orientation
     */
    public SimpleRail(Orientation orientation) {
        super();
        this.orientation = orientation;
    }

    /**
     * @param orientation new orientation to be set to the SimpleRail
     */
    public void setOrientation(Orientation orientation){
    	this.orientation = orientation;
    }

    /**
     * @param from The rail where the train comes from
     * @return The next rail
     */
    @Override
    public Rail next(Rail from) {
        if(links.indexOf(from) == 0) {
            return links.size() > 1 ? links.get(1) : null;
        } else {
            return links.size() > 0 ? links.get(0) : null;
        }
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    @Override
    public String getDrawData() {
        if (coach != null)
            return coach.getDrawData();

        switch(orientation){
            case HORIZONTAL: return "═";
            case VERTICAL: return "║";
            case TOP_LEFT: return "╝";
            case TOP_RIGHT: return "╚";
            case BOTTOM_LEFT: return "╗";
            case BOTTOM_RIGHT: return "╔";
            default: return " ";
        }
    }
}
