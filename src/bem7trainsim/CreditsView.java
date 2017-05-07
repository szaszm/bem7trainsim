package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Kristóf on 2017. 05. 07..
 */
public class CreditsView extends View {
    /**
     * Stores the height of the line in pixels
     */
    static int lineHeight = 20;

    /**
     * The controller state which shows this view
     */
    private CreditsControllerState state;

    /**
     * CreditsView constructor.
     * @param state The controller state which shows this view
     */
    public CreditsView(CreditsControllerState state) {
        this.state = state;
    }

    /**
     * Handles mouse clicks
     * @param e Event data
     */
    @Override
    void mouseClicked(MouseEvent e) {
        state.handleCommand("exit");
    }

    /**
     * Draws the content
     * @param g Graphics object
     */
    @Override
    void draw(Graphics g) {
        g.drawString("Csutorás Robin",g.getClipBounds().width / 2 - 45, g.getClipBounds().height / 2 - lineHeight * 2);
        g.drawString("Gnandt Balázs",g.getClipBounds().width / 2 - 40, g.getClipBounds().height / 2 - lineHeight);
        g.drawString("Szász Márton",g.getClipBounds().width / 2 - 35, g.getClipBounds().height / 2);
        g.drawString("Tamás Csongor",g.getClipBounds().width / 2 - 45, g.getClipBounds().height / 2 + lineHeight);
        g.drawString("Zabó Kristóf",g.getClipBounds().width / 2 - 32, g.getClipBounds().height / 2 + lineHeight * 2);
        g.drawString("Back",g.getClipBounds().width / 2 - 12, g.getClipBounds().height / 2 + lineHeight * 3);
    }
}
