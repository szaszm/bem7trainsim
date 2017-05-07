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
        if(e.getX() >= 100 && e.getX() <= 800) {
            if(e.getY() >= 50 && e.getY() <= 200){
                entries.get(0).click();
            }
            if(e.getY() >= 300 && e.getY() <= 450){
                entries.get(1).click();
            }
            if(e.getY() >= 550 && e.getY() <= 700) {
                entries.get(2).click();
            }
        }
    }

    @Override
    void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(100, 50, g.getClipBounds().width - 200,150 );
        g.fillRect(100, 300, g.getClipBounds().width - 200,150 );
        g.fillRect(100, 550, g.getClipBounds().width - 200,150 );
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times New Roman", Font.BOLD, 45));
        g.drawString("Play", g.getClipBounds().width / 2 - 45,  135);
        g.drawString("Credits", g.getClipBounds().width / 2 - 70,  385);
        g.drawString("Quit", g.getClipBounds().width / 2 - 45,  635);
    }
}
