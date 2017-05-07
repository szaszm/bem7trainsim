package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by Kristóf on 2017. 05. 07..
 */
public class CreditsView extends View {


    public CreditsView() {
    }

    @Override
    void mouseClicked(MouseEvent e) {

    }

    @Override
    void draw(Graphics g) {
        g.drawString("Csutorás Robin\nGnandt Balázs\nSzász Márton\nTamás Csongor\nZabó Kristóf",g.getClipBounds().width / 2, g.getClipBounds().height / 2);
    }
}
