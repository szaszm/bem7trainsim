package bem7trainsim;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Csuto on 4/18/2017.
 * Represents the state of the controller when the user is able to choose from playing,
 * showing the credits or quitting the game.
 */
public class MainMenuControllerState extends ControllerState {
    protected MainMenuControllerState(Controller c) {
        super(c);
        List<MenuEntry> entries = new ArrayList<>();
        // TODO: fill entries
        entries.add(new MenuEntry("PLAY"));
        entries.add(new MenuEntry("CREDITS"));
        entries.add(new MenuEntry("EXIT"));
        view = new MenuView(entries);
    }

    private MenuView getView() {
        return (MenuView) view;
    }

    @Override
    public ControllerState handleCommand(String command) {
        String[] s = command.split(" ");
        switch (s[0]) {
            case "play": {
                LevelSelectControllerState state = new LevelSelectControllerState(controller);
                controller.setState(state);
                return state;
            }
            case "credits": {
                CreditsControllerState state = new CreditsControllerState(controller);
                controller.setState(state);
                return state;
            }
            case "exit":
                return null;
        }
        return this;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    public void click(MenuEntry m){
        if(m.GetLabel().equals("PLAY")) {
            controller.setState(new LevelSelectControllerState(controller));
        } else if(m.GetLabel().equals("CREDITS")){
            controller.setState(new CreditsControllerState(controller));
        } else if(m.GetLabel().equals("EXIT")){
            //TODO kilépés
        }
    }
}
