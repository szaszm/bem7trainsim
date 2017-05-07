package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents a view
 * Created by marci on 5/7/17.
 */
public abstract class View {

    /**
     * Handles mouse click events
     * @param e Event data
     */
    abstract void mouseClicked(MouseEvent e);

    /**
     * Draws the scene
     * @param g Graphics object
     */
    abstract void draw(Graphics g);

}
