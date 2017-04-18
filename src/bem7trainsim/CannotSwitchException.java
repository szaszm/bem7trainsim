package bem7trainsim;

/**
 * Created by Csuto on 4/9/2017.
 * Thrown when the switch is not able to switch
 */
public class CannotSwitchException extends Exception {
    public CannotSwitchException(String msg) {
        super(msg);
    }
}
