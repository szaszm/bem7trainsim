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
        controllerState.clickAt(e.getX() / sideLength, e.getY() / sideLength);
    }

    /**
     * Draws the scene
     * @param g Graphics object
     */
    @Override
    void draw(Graphics g) {
        g.drawString(Integer.toString(controllerState.getCurrentTime()),0,0);
        FieldDrawData[][] fdd = table.getDrawData();
        sideLength = g.getClipBounds().height / fdd.length;
        if (fdd[0] != null && g.getClipBounds().width / fdd[0].length < sideLength) {
            sideLength = g.getClipBounds().width / fdd[0].length;
        }
        for (int y = 0; y < fdd.length; y++) {
            FieldDrawData[] row = fdd[y];
            for (int x = 0; x < row.length; x++) {
                FieldDrawData field = row[x];
                field.draw(g,x,y,sideLength,sideLength);
            }
        }
    }

    private int sideLength = 50;
}
