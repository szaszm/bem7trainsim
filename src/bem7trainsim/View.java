package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marci on 5/7/17.
 */
public abstract class View {
    protected Graphics graphics;

    public View(Graphics g) {
        graphics = g;
    }

    abstract void mouseClicked(MouseEvent e);
    abstract void draw();
}
