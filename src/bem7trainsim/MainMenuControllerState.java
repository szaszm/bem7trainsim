package bem7trainsim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Csuto on 4/18/2017.
 * Represents the state of the controller when the user is able to choose from playing,
 * showing the credits or quitting the game.
 */
public class MainMenuControllerState extends ControllerState {
    /**
     * MainMenuControllerState constructor
     * @param c The controller
     */
    protected MainMenuControllerState(Controller c) {
        super(c);
        List<MenuEntry> entries = new ArrayList<>();
        // TODO: fill entries
        entries.add(new MenuEntry("PLAY", this));
        entries.add(new MenuEntry("CREDITS", this));
        entries.add(new MenuEntry("EXIT", this));
        view = new MenuView(entries);
    }

    /**
     * Returns the view properly downcasted
     * @return The view
     */
    private MenuView getView() {
        return (MenuView) view;
    }

    /**
     * Handles textual commands
     * @param command The command in string format. Available commands list is in the documentation.
     * @return The new controller state
     */
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


    /**
     * Handles menu entry selection
     * @param m The selected menu entry
     */
    public void click(MenuEntry m){
        if(m.GetLabel().equals("PLAY")) {
            controller.setState(new LevelSelectControllerState(controller));
        } else if(m.GetLabel().equals("CREDITS")){
            controller.setState(new CreditsControllerState(controller));
        } else if(m.GetLabel().equals("EXIT")){
            controller.setState(null);
        }
    }
}
