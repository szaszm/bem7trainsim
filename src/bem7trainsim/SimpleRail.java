package bem7trainsim;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a simple basic rail type
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
     * Sets the orientation of the rail
     * @param orientation new orientation to be set to the SimpleRail
     */
    public void setOrientation(Orientation orientation){
    	this.orientation = orientation;
    }

    /**
     * Returns the next rail given the previous one
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
    public FieldDrawData getDrawData() {
        List<String> layers = new ArrayList<>();

        switch(orientation){
            case HORIZONTAL:
                layers.add("═");
                break;
            case VERTICAL:
                layers.add("║");
                break;
            case TOP_LEFT:
                layers.add("╝");
                break;
            case TOP_RIGHT:
                layers.add("╚");
                break;
            case BOTTOM_LEFT:
                layers.add("╗");
                break;
            case BOTTOM_RIGHT:
                layers.add("╔");
                break;
            default:
                layers.add(" ");
                break;
        }

        if (coach != null)
            layers.addAll(coach.getDrawData());

        return new FieldDrawData(layers);
    }
}
