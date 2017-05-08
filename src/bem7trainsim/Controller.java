package bem7trainsim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

/**
 * Created by Csuto on 4/9/2017.
 * Controls the application
 */
public class Controller extends JFrame implements MouseListener, AutoCloseable {

    /**
     * The current state of the controller
     */
    private ControllerState state;

    /**
     * Creates the controller and shows the main window
     */
    public Controller() {
        super("best_ever_magic_7 Train Simulator");
        setContentPane(new DrawPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(900, 900);
        setState(new MainMenuControllerState(this));
        addMouseListener(this);
    }

    /**
     * Sets the state of the controller
     * @param state The new state
     */
    public void setState(ControllerState state) {
        if(state == null){
            try {
                close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.state = state;
    }

    /**
     * Handles mouse clicks
     * @param e Event data
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        state.mouseClicked(e);
    }

    /**
     * Handles mouse button down events
     * @param e Event data
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Handles mouse button up events
     * @param e Event data
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Handles mouse enter events
     * @param e Event data
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Handles mouse leave events
     * @param e Event data
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Closes the main window and exits the application
     */
    @Override
    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Makes drawing possible
     */
    class DrawPane extends JPanel{
        /**
         * Draws the component
         * @param g Graphics object
         */
        public void paintComponent(Graphics g){
            state.view.draw(g);
        }
    }

    /**
     * Redraws the frame
     */
    public void Redraw() {
        invalidate();
        repaint(0, 0, getWidth(), getHeight());
        revalidate();
    }
}
