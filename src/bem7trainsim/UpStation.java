package bem7trainsim;

import java.awt.*;

/**
 * Created by Csuto on 4/9/2017.
 */
public class UpStation extends SimpleRail {
    private Color color;
    private boolean gone;
    public UpStation(Color color) {
        this.color = color;
        this.gone = false;
    }
    public void arrive(Train t) {
        //TODO:
        if (!this.gone) {
            if (t.getOn(color)) {
                gone = true;
            }
        }
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
