package bem7trainsim;

import java.awt.*;
import java.util.*;

/**
 * Represents a station where passengers can get up
 * Created by Csuto on 4/9/2017.
 */
public class UpStation extends SimpleRail {
    /**
     * Color of the station
     */
    private Color color;

    /**
     * True if the passengers are gone
     */
    private boolean gone;


    /**
     * Creating az upstation with the color and orientation given.
     * @param color Color of the station
     * @param orientation Orientation of the station
     */
    public UpStation(Color color, Orientation orientation) {
        super(orientation);
        this.color = color;
        this.gone = false;
    }

    /**
     * Handles the event when a train arriving to this rail
     * @param t The train which is arrived
     * @throws CollisionException Thrown if the rail already has a train
     */
    public void arrive(Train t) throws CollisionException {
        super.arrive(t);
        if (!this.gone) {
            if (t.getOn(color)) {
                gone = true;
            }
        }
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    @Override
    public FieldDrawData getDrawData() {
        ArrayList<String> layers = new ArrayList<>();

        String s = "";

        if (orientation == SimpleRail.Orientation.HORIZONTAL){
            s += "═";
        } else {
            s += "║";
        }

        if(!gone){
            if (color.equals(Color.RED)) {
                s += "i";
            }
            else if (color.equals(Color.YELLOW)) {
                s += "á";
            }
            else if (color.equals(Color.GREEN)) {
                s += "ö";
            }
            else if (color.equals(Color.BLUE)) {
                s += "é";
            } else {
                s += "g";
            }
        }

        layers.add(s);

        if (coach != null)
            layers.add(coach.getDrawData());

        return new FieldDrawData(layers);
    }


    /**
     * Returns whether passengers have left the station or not
     * @return true if gone, else false
     */
    public boolean isGone(){
        return gone;
    }
}
