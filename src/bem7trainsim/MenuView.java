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
        if(e.getX() >= 100 && e.getX() <= 800) {
            if(e.getY() >= 50+30 && e.getY() <= 200+30){
                entries.get(0).click();
            }
            if(e.getY() >= 300+30 && e.getY() <= 450+30){
                entries.get(1).click();
            }
            if(e.getY() >= 550+30 && e.getY() <= 700+30) {
                entries.get(2).click();
            }
        }
    }

    /**
     * Draws the scene
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
        g.drawString("Play", g.getClipBounds().width / 2 - 45,  140);
        g.drawString("Credits", g.getClipBounds().width / 2 - 70,  390);
        g.drawString("Quit", g.getClipBounds().width / 2 - 45,  645);
    }
}
