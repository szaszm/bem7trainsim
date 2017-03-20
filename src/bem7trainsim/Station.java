package bem7trainsim;
import java.awt.*;

/**
 * Created by marci on 2017.03.18..
 */
public class Station extends SimpleRail {
    private Color color;

    public Station(Color color) {
        super();
        this.color = color;
        System.out.println("LETREJOTT: "+Main.identityToString(this)+" SZIN "+color.toString());
    }

    public void arrive(Train train) {
        train.empty(color);
        System.out.println("URITES PROBA:"+Main.identityToString(this)+" -> "+Main.identityToString(train));
    }
}
