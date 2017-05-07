package bem7trainsim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

/**
 * Created by Csuto on 4/18/2017.
 * Represents the different states of the controller
 */
abstract public class ControllerState implements ActionListener {
    protected Controller controller;
    protected Timer timerDraw = new Timer(1000 / 60, this);
    protected View view;
    protected Graphics graphics;

    protected ControllerState(Graphics g, Controller c) {
        controller = c;
        graphics = g;
    }

    /**
     * @param command The command in string format. Available commands list is in the documentation.
     * @return The controller state which the controller steps into
     */
    public abstract ControllerState handleCommand(String command);
    public abstract void changedTo();

    public abstract void mouseClicked(MouseEvent e);

    @Override
    public void actionPerformed(ActionEvent e) {
        if(view != null) view.draw();
    }
}
