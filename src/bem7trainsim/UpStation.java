package bem7trainsim;

import java.awt.*;

/**
 * Created by Csuto on 4/9/2017.
 */
public class UpStation extends SimpleRail {
    private Color color;
    private boolean gone;
    public UpStation(Color color, Orientation orientation) {
        super(orientation);
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
        if (coach != null)
            return coach.getDrawData();
        if (color.equals(Color.RED)) {
            return "i";
        }
        else if (color.equals(Color.YELLOW)) {
            return "á";
        }
        else if (color.equals(Color.GREEN)) {
            return "ö";
        }
        else if (color.equals(Color.GREEN)) {
            return "é";
        }
        else if (orientation == SimpleRail.Orientation.HORIZONTAL){
        	return "═";
        }
        else if (orientation == SimpleRail.Orientation.VERTICAL){
        	return "║";
        }
        else return " ";
    }
}
