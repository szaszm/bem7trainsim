package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Kristóf on 2017. 05. 07..
 */
public class CreditsView extends View {

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
        g.setColor(Color.RED);
        g.fillRect(100, 50, g.getClipBounds().width - 200,150 );
        g.fillRect(100, 300, g.getClipBounds().width - 200,150 );
        g.fillRect(100, 550, g.getClipBounds().width - 200,150 );
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, 45));
        g.drawString("Credits",g.getClipBounds().width / 2 - 70,  135);
        g.drawString("Back",g.getClipBounds().width / 2 - 45, 635);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString("Csutorás Robin, Gnandt Balázs, Szász Márton",g.getClipBounds().width / 2 - 285, 350);
        g.drawString("Tamás Csongor, Zabó Kristóf",g.getClipBounds().width / 2 - 190, 400);
    }
}
