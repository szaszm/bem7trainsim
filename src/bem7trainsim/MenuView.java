package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Represents the view of the menu
 * Created by marci on 5/7/17.
 */
public class MenuView extends View {
    /**
     * Stores menu entries
     */
    List<? extends MenuEntry> entries;

    /**
     * MenuView constructor
     * @param entries Shown menu entries
     */
    public MenuView(List<? extends MenuEntry> entries) {
        super();
        this.entries = entries;
    }

    /**
     * Handles mouse clicks and determines which menu entry was clicked on if any
     * @param e Event data
     */
    @Override
    void mouseClicked(MouseEvent e) {

    }

    /**
     * Draws the scene
     * @param g Graphics object
     */
    @Override
    void draw(Graphics g) {

    }
}
