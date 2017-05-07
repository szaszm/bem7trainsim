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

    }
}
