package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by marci on 5/7/17.
 */
public class MenuView extends View {
    List<? extends MenuEntry> entries;
    public MenuView(List<? extends MenuEntry> entries) {
        super();
        this.entries = entries;
    }

    @Override
    void mouseClicked(MouseEvent e) {

    }

    @Override
    void draw(Graphics g) {

    }
}
