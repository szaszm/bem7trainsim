package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Kristóf on 2017. 05. 07..
 */
public class CreditsView extends View {
    static int lineHeight = 20;

    public CreditsView() {
    }

    @Override
    void mouseClicked(MouseEvent e) {

    }

    @Override
    void draw(Graphics g) {
        g.drawString("Csutorás Robin",g.getClipBounds().width / 2 - 45, g.getClipBounds().height / 2 - lineHeight * 2);
        g.drawString("Gnandt Balázs",g.getClipBounds().width / 2 - 40, g.getClipBounds().height / 2 - lineHeight);
        g.drawString("Szász Márton",g.getClipBounds().width / 2 - 35, g.getClipBounds().height / 2);
        g.drawString("Tamás Csongor",g.getClipBounds().width / 2 - 45, g.getClipBounds().height / 2 + lineHeight);
        g.drawString("Zabó Kristóf",g.getClipBounds().width / 2 - 32, g.getClipBounds().height / 2 + lineHeight * 2);
        g.drawString("Back",g.getClipBounds().width / 2 - 12, g.getClipBounds().height / 2 + lineHeight * 3);
    }
}
