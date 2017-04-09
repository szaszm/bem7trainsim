package bem7trainsim;

import java.awt.*;

/**
 * Created by Csuto on 4/9/2017.
 */
public class UpStation extends SimpleRail {
    private Color color;
    public UpStation(Color color) {
        this.color = color;
    }
    public void arrive(Train t) {
        //TODO:
    }
    @Override
    public String getDrawData() {
        if (color.equals(Color.RED)) {
            return "p";
        }
        else if (color.equals(Color.YELLOW)) {
            return "s";
        }
        else if (color.equals(Color.GREEN)) {
            return "z";
        }
        return "k";
    }
}
