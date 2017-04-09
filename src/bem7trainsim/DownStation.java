package bem7trainsim;
import java.awt.*;

/**
 * Created by marci on 2017.03.18..
 */
public class DownStation extends SimpleRail {
    private Color color;

    public DownStation(Color color) {
        super();
        this.color = color;
        System.out.println("LETREJOTT: "+Main.identityToString(this)+" SZIN "+color.toString());
    }

    public void arrive(Train train) throws CollisionException {
        train.empty(color);
        System.out.println("URITES PROBA:"+Main.identityToString(this)+" -> "+Main.identityToString(train));
    }

    public String getDrawData() {
        if (color.equals(Color.RED)) {
            return "P";
        }
        else if (color.equals(Color.YELLOW)) {
            return "S";
        }
        else if (color.equals(Color.GREEN)) {
            return "Z";
        }
        return "K";
    }
}
