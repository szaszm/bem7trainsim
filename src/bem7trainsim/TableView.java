package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marci on 5/7/17.
 */
public class TableView extends View {

    /**
     * Current state of the field
     */
    private Table table;

    private PlayControllerState controllerState;

    public TableView(Table table, PlayControllerState pcs) {
        super();
        this.table = table;
        controllerState = pcs;
    }

    @Override
    void mouseClicked(MouseEvent e) {

    }

    @Override
    void draw(Graphics g) {
        FieldDrawData[][] fdd = table.getDrawData();
        int sideLength = g.getClipBounds().width / fdd.length;
        if (fdd[0] != null && g.getClipBounds().height / fdd[0].length < sideLength) {
            sideLength = g.getClipBounds().height / fdd[0].length;
        }
        for (int y = 0; y < fdd.length; y++) {
            FieldDrawData[] row = fdd[y];
            for (int x = 0; x < row.length; x++) {
                FieldDrawData field = row[x];
                field.draw(g,x,y,sideLength,sideLength);
            }
        }
    }
}
