package bem7trainsim;

/**
 * Created by Csuto on 4/18/2017.
 * Represents the different states of the controller
 */
abstract public class ControllerState {
    /**
     * @param command The command in string format. Available commands list is in the documentation.
     * @return The controller state which the controller steps into
     */
    public abstract ControllerState handleCommand(String command);
    public abstract void changedTo();
}
