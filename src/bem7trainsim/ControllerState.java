package bem7trainsim;

/**
 * Created by Csuto on 4/18/2017.
 */
abstract public class ControllerState {
    public abstract ControllerState handleCommand(String command);
}
