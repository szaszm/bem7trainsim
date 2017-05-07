package bem7trainsim;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class CreditsControllerState extends ControllerState implements ActionListener {

    protected CreditsControllerState(Controller c) {
        super(c);
        view = new CreditsView();
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