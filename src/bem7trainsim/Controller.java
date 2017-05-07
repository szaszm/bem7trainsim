package bem7trainsim;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Csuto on 4/9/2017.
 * Controls the application
 */
public class Controller extends JFrame implements MouseListener, AutoCloseable {

    private ControllerState state;

    public Controller() {
        super("best_ever_magic_7 Train Simulator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void start() {
        state = new MainMenuControllerState(getGraphics(), this);
    	state.changedTo();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        while (state != null) {
            try {
                ControllerState newState = state.handleCommand(buffer.readLine());
                if (newState != state) {
                    state = newState;
                    if (newState != null) {
                        state.changedTo();
                    }
                }
            } catch (IOException e) {
                // Shall never happen
                e.printStackTrace();
            }
        }
    }

    public void setState(ControllerState state) {
        this.state = state;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        state.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void close() throws Exception {
        setVisible(false);
    }
}
