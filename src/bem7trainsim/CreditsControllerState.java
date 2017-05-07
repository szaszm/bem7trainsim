package bem7trainsim;

import java.awt.event.ActionListener;

/**
 * The controller state which shows credits
 */
public class CreditsControllerState extends ControllerState implements ActionListener {

    /**
     * CreditsControllerState constructor
     * @param c The controller
     */
    protected CreditsControllerState(Controller c) {
        super(c);
        view = new CreditsView(this);
    }

    /**
     * Returns the current view downcasted properly
     * @return The view
     */
    private CreditsView getView() { return (CreditsView) view; }

    /**
     * Handles textual commands
     * @param command The command in string format. Available commands list is in the documentation.
     * @return The new controller state
     */
    @Override
    public ControllerState handleCommand(String command) {
        return null;
    }
}