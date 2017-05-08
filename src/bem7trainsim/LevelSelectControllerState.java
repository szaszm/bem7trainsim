package bem7trainsim;

import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Csuto on 4/18/2017.
 * represents the controller state where the user can choose the level
 */
public class LevelSelectControllerState extends ControllerState {

    /**
     * LevelSelectControllerState constructor
     * @param c The controller
     */
    protected LevelSelectControllerState(Controller c) {
        super(c);
        view = new LevelSelectView();
    }

    /**
     * Handles textual commands
     * @param command The command in string format. Available commands list is in the documentation.
     * @return The new controller state
     */
    @Override
    public ControllerState handleCommand(String command) {
        String[] s = command.split(" ");
        if(s[0].startsWith("map_")){
            try{
                PlayControllerState newState = new PlayControllerState(controller, s[0].substring(4));
                newState.start();
                controller.setState(newState);
                return newState;
            } catch(IOException | TableLeftException | CollisionException e){
                message(e.getMessage());
                MainMenuControllerState state = new MainMenuControllerState(controller);
                controller.setState(state);
                return state;
            }
        } else if (s[0].startsWith("test_")){
            try{
                TestControllerState newState = new TestControllerState(controller, s[0].substring(5));
                newState.start();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
            LevelSelectControllerState state = new LevelSelectControllerState(controller);
            controller.setState(state);
            return state;
        } else if (s[0].equals("back")){
            MainMenuControllerState state = new MainMenuControllerState(controller);
            controller.setState(state);
            return state;
        }
        return this;
    }
}
