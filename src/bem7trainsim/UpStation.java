package bem7trainsim;

import java.awt.*;

/**
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
     * @param color
     * @param orientation
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
    public String getDrawData() {
        if (coach != null)
            return coach.getDrawData();

        if (gone && orientation == SimpleRail.Orientation.HORIZONTAL){
            return "═";
        }
        else if (gone && orientation == SimpleRail.Orientation.VERTICAL){
            return "║";
        }
        else if (color.equals(Color.RED)) {
            return "i";
        }
        else if (color.equals(Color.YELLOW)) {
            return "á";
        }
        else if (color.equals(Color.GREEN)) {
            return "ö";
        }
        else if (color.equals(Color.BLUE)) {
            return "é";
        }
        else return " ";
    }


    /**
     * @return true if gone, else false
     */
    public boolean isGone(){
        return gone;
    }
}
