package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by marci on 5/7/17.
 */
public abstract class View {

    abstract void mouseClicked(MouseEvent e);

    abstract void draw(Graphics g);
}
