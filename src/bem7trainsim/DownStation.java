package bem7trainsim;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.18.
 * Represents the stations where the passengers can get down from the trains
 */
public class DownStation extends SimpleRail {
    /**
     * The color of the station
     */
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
    public FieldDrawData getDrawData() {
        List<String> layers = new ArrayList<>();
        String s = "";
        if(orientation == Orientation.HORIZONTAL){
            s += "═";
        } else {
            s += "║";
        }

        if (color.equals(Color.RED)) {
            s += "I";
        }
        else if (color.equals(Color.YELLOW)) {
            s += "Á";
        }
        else if (color.equals(Color.GREEN)) {
            s += "Ö";
        }
        else s += "É";

        layers.add(s);

        if (coach != null)
            layers.addAll(coach.getDrawData());

        return new FieldDrawData(layers);
    }
}
