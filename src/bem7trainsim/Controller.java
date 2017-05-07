package bem7trainsim;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        setState(new MainMenuControllerState(this));
    }

    public void setState(ControllerState state) {
        this.state = state;
        state.changedTo();
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
        setVisible(false);
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
