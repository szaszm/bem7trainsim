package bem7trainsim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Created by Csuto on 4/18/2017.
 * Represents the different states of the controller
 */
abstract public class ControllerState implements ActionListener {
    /**
     * The controller of the application
     */
    protected Controller controller;

    /**
     * Periodically redraws the field
     */
    protected Timer timerDraw = new Timer(1000 / 30, this);

    /**
     * The active view
     */
    protected View view;

    /**
     * The abstract base class constructor
     * @param c Reference to the controller
     */
    protected ControllerState(Controller c) {
        controller = c;
    }

    /**
     * Handles a textual command
     * @param command The command in string format. Available commands list is in the documentation.
     * @return The controller state which the controller steps into
     */
    public abstract ControllerState handleCommand(String command);

    /**
     * Mouse click event handler
     * @param e Mouse event
     */
    public void mouseClicked(MouseEvent e){
        view.mouseClicked(e);
    }

    /**
     * Timer tick handler, redraws the drawing area
     * @param e Event var
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        controller.invalidate();
    }
}
