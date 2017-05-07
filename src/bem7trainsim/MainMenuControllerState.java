package bem7trainsim;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Csuto on 4/18/2017.
 * Represents the state of the controller when the user is able to choose from playing,
 * showing the credits or quitting the game.
 */
public class MainMenuControllerState extends ControllerState {
    protected MainMenuControllerState(Graphics g, Controller c) {
        super(g, c);
        List<? extends MenuEntry> entries = new ArrayList<>();
        // TODO: fill entries
        view = new MenuView(g, entries);
    }

    private MenuView getView() { return (MenuView) view; }

    @Override
    public ControllerState handleCommand(String command) {
        String[] s = command.split(" ");
        switch (s[0]) {
            case "play":
                LevelSelectControllerState state = new LevelSelectControllerState(graphics, controller);
                controller.setState(state);
                return state;
            case "credits":
                System.out.println("Csutorás Robin\nGnandt Balázs\nSzász Márton\nTamás Csongor\nZabó Kristóf");
                break;
            case "exit":
                return null;
        }
        return this;
    }
    @Override
    public void changedTo() {
        System.out.println("MAIN_MENU");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
