package bem7trainsim;
import java.awt.*;

/**
 * Created by marci on 2017.03.18..
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

    public void arrive(Train train) throws CollisionException {
        train.empty(color);
    }

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
