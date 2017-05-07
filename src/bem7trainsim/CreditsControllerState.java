package bem7trainsim;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class CreditsControllerState extends ControllerState implements ActionListener {

    protected CreditsControllerState(Graphics g, Controller c) {
        super(g, c);
        view = new CreditsView(g);
    }

    private CreditsView getView() { return (CreditsView) view; }

    @Override
    public ControllerState handleCommand(String command) {
        return null;
    }

    @Override
    public void changedTo() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}