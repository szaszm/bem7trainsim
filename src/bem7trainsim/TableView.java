package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Represents the view of the whole table
 * Created by marci on 5/7/17.
 */
public class TableView extends View {

    /**
     * Current state of the field
     */
    private Table table;

    /**
     * The controller state while playing
     */
    private PlayControllerState controllerState;

    /**
     * Whether it is started
     */
    public boolean started;

    /**
     * TableView constructor
     * @param table The table shown
     * @param pcs Controller state
     */
    public TableView(Table table, PlayControllerState pcs) {
        super();
        this.table = table;
        controllerState = pcs;
    }

    /**
     * Handles mouse click events
     * @param e Event datThe a
     */
    @Override
    void mouseClicked(MouseEvent e) {
        if (!started) {
            controllerState.clickAt(0,0);
            return;
        }
        controllerState.clickAt(e.getX() / sideLength, (e.getY() - topHeight) / sideLength);
    }

    /**
     * Top overlay height
     */
    int topHeight = 100;

    /**
     * Draws the scene
     * @param g Graphics object
     */
    @Override
    void draw(Graphics g) {
        FieldDrawData[][] fdd = table.getDrawData();
        sideLength = (g.getClipBounds().height - topHeight) / fdd.length;
        if (fdd[0] != null && g.getClipBounds().width / fdd[0].length < sideLength) {
            sideLength = g.getClipBounds().width / fdd[0].length;
        }
        for (int y = 0; y < fdd.length; y++) {
            FieldDrawData[] row = fdd[y];
            for (int x = 0; x < row.length; x++) {
                FieldDrawData field = row[x];
                field.draw(g,x * sideLength,y * sideLength + topHeight,sideLength,sideLength);
            }
        }
        g.setColor(Color.PINK);
        g.fillRect(0,0,g.getClipBounds().width, topHeight);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        String time = Integer.toString(controllerState.getCurrentTime());
        g.drawString(time,50 - time.length() * 7,30);
        if (!started) {
            g.setColor(Color.PINK);
            g.fillRect(g.getClipBounds().width / 2 - 20,g.getClipBounds().height / 2 - 35, 100, 50);
            g.setColor(Color.BLACK);
            g.drawString("Start",g.getClipBounds().width / 2,g.getClipBounds().height / 2);
        }
    }

    private int sideLength = 50;
}
