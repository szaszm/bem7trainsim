package bem7trainsim;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Csuto on 4/18/2017.
 * represents the controller state where the user can choose the level
 */
public class LevelSelectControllerState extends ControllerState {

    protected LevelSelectControllerState(Graphics g, Controller c) {
        super(g, c);
        view = new LevelSelectView(g);
    }

    @Override
    public ControllerState handleCommand(String command) {
        String[] s = command.split(" ");
        if(s[0].startsWith("map_")){
            try{
                PlayControllerState newState = new PlayControllerState(graphics, controller, s[0].substring(4));
                newState.start();
                controller.setState(newState);
                return newState;
            } catch(IOException e){
                System.out.println(e.getMessage());
                MainMenuControllerState state = new MainMenuControllerState(graphics, controller);
                controller.setState(state);
                return state;
            } catch (CollisionException e) {
                System.out.println("Utkozes, jatek vege. Ido: 0");
                MainMenuControllerState state = new MainMenuControllerState(graphics, controller);
                controller.setState(state);
                return state;
            } catch (TableLeftException e){
                System.out.println("Nem ures vonat elhagyta a palyat, jatek vege. Ido: 0");
                MainMenuControllerState state = new MainMenuControllerState(graphics, controller);
                controller.setState(state);
                return state;
            }
        } else if (s[0].startsWith("test_")){
            try{
                TestControllerState newState = new TestControllerState(s[0].substring(5));
                newState.start();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
            LevelSelectControllerState state = new LevelSelectControllerState(graphics, controller);
            controller.setState(state);
            return state;
        } else if (s[0].equals("back")){
            MainMenuControllerState state = new MainMenuControllerState(graphics, controller);
            controller.setState(state);
            return state;
        }
        return this;
    }
    @Override
    public void changedTo() {
        System.out.println("LEVEL_MENU");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
}
