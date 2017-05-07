package bem7trainsim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

/**
 * Created by Csuto on 4/9/2017.
 * Controls the application
 */
public class Controller extends JFrame implements MouseListener, AutoCloseable {

    private ControllerState state;

    public Controller() {
        super("best_ever_magic_7 Train Simulator");
        setContentPane(new DrawPane());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setSize(900, 900);
        setState(new MainMenuControllerState(this));
    }

    public void setState(ControllerState state) {
        this.state = state;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        state.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void close() throws Exception {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void Invalidate() {
        repaint(0, 0, getWidth(), getHeight());
        revalidate();
    }
    class DrawPane extends JPanel{
        public void paintComponent(Graphics g){
            state.view.draw(g);
        }
    }
}
