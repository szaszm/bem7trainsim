package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Represents the level selector view
 * Created by marci on 5/7/17.
 */
public class LevelSelectView extends View {

    /**
     * The controller state which shows this view
     */
    private LevelSelectControllerState state;

    /**
     * Stores maps name
     */
    private List<String> maplist;

    /**
     * Size of maplist
     */
    private int mapsize;

    /**
     * LevelSelectView constructor
     */
    public LevelSelectView(LevelSelectControllerState state) {
        this.state = state;
        maplist = state.getLevels();
        mapsize = maplist.size();
    }

    /**
     * Handles mouse clicks
     *
     * @param e Event data
     */
    @Override
    void mouseClicked(MouseEvent e) {
        if (e.getX() >= 100 && e.getX() <= 800 && e.getY() >= 550 + 30 && e.getY() <= 700 + 30) {
            state.handleCommand("back");
            return;
        }
        if (e.getX() >= 100 && e.getX() <= 250 && e.getY() >= 50 + 30 && e.getY() <= 200 + 30 && mapsize > 0) {
            state.handleCommand("map_" + maplist.get(0));
            return;
        }
        if (e.getX() >= 283 && e.getX() <= 333 && e.getY() >= 50 + 30 && e.getY() <= 200 + 30 && mapsize > 1) {
            state.handleCommand("map_" + maplist.get(1));
            return;
        }
        if (e.getX() >= 466 && e.getX() <= 616 && e.getY() >= 50 + 30 && e.getY() <= 200 + 30 && mapsize > 2) {
            state.handleCommand("map_" + maplist.get(2));
            return;
        }
        if (e.getX() >= 649 && e.getX() <= 799 && e.getY() >= 50 + 30 && e.getY() <= 200 + 30 && mapsize > 3) {
            state.handleCommand("map_" + maplist.get(3));
            return;
        }
        if (e.getX() >= 100 && e.getX() <= 250 && e.getY() >= 300 + 30 && e.getY() <= 450 + 30 && mapsize > 4) {
            state.handleCommand("map_" + maplist.get(4));
            return;
        }
        if (e.getX() >= 283 && e.getX() <= 333 && e.getY() >= 300 + 30 && e.getY() <= 450 + 30 && mapsize > 5) {
            state.handleCommand("map_" + maplist.get(5));
            return;
        }
        if (e.getX() >= 466 && e.getX() <= 616 && e.getY() >= 300 + 30 && e.getY() <= 450 + 30 && mapsize > 6) {
            state.handleCommand("map_" + maplist.get(6));
            return;
        }
        if (e.getX() >= 649 && e.getX() <= 799 && e.getY() >= 300 + 30 && e.getY() <= 450 + 30 && mapsize > 7) {
            state.handleCommand("map_" + maplist.get(7));
            return;
        }
    }

    /**
     * Draws the level selector
     *
     * @param g Graphics object
     */
    @Override
    void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(100, 50, 150, 150);
        g.fillRect(283, 50, 150, 150);
        g.fillRect(466, 50, 150, 150);
        g.fillRect(649, 50, 150, 150);
        g.fillRect(100, 300, 150, 150);
        g.fillRect(283, 300, 150, 150);
        g.fillRect(466, 300, 150, 150);
        g.fillRect(649, 300, 150, 150);
        g.fillRect(100, 550, g.getClipBounds().width - 200 + 20, 150);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, 45));
        g.drawString("Back", g.getClipBounds().width / 2 - 45, 645);
        if (mapsize > 0) g.drawString("1", 165, 135);
        if (mapsize > 1) g.drawString("2", 348, 135);
        if (mapsize > 2) g.drawString("3", 532, 135);
        if (mapsize > 3) g.drawString("4", 714, 135);
        if (mapsize > 4) g.drawString("5", 165, 385);
        if (mapsize > 5) g.drawString("6", 348, 385);
        if (mapsize > 6) g.drawString("7", 532, 385);
        if (mapsize > 7) g.drawString("8", 714, 385);
    }
}
