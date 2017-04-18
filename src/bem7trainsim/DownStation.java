package bem7trainsim;
import java.awt.*;

/**
 * Created by marci on 2017.03.18.
 * Represents the stations where the passengers can get down from the trains
 */
public class DownStation extends SimpleRail {
    private Color color;

    /**
     * Creates a station with given color where people can get off the train
     * @param color the color of the station
     */
    public DownStation(Color color, SimpleRail.Orientation orientation) {
        super(orientation);
        this.color = color;
    }

    /**
     * @param train The train which is arrived
     * @throws CollisionException Thrown if the rail already has a train
     */
    public void arrive(Train train) throws CollisionException {
        super.arrive(train);
        train.empty(color);
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    @Override
    public String getDrawData() {
        if (coach != null)
            return coach.getDrawData();
        if (color.equals(Color.RED)) {
            return "I";
        }
        else if (color.equals(Color.YELLOW)) {
            return "Á";
        }
        else if (color.equals(Color.GREEN)) {
            return "Ö";
        }
        return "É";
    }
}
