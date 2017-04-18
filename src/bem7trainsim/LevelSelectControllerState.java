package bem7trainsim;

import java.io.IOException;

/**
 * Created by Csuto on 4/18/2017.
 * represents the controller state where the user can choose the level
 */
public class LevelSelectControllerState extends ControllerState {

    @Override
    public ControllerState handleCommand(String command) {
        String[] s = command.split(" ");
        if(s[0].startsWith("map_")){
            try{
                PlayControllerState newState = new PlayControllerState(s[0].substring(4));
                newState.start();
                return newState;
            } catch(IOException e){
                System.out.println(e.getMessage());
                return new MainMenuControllerState();
            } catch (CollisionException e) {
                System.out.println("Utkozes, jatek vege. Ido: 0");
                return new MainMenuControllerState();
            } catch (TableLeftException e){
                System.out.println("Nem ures vonat elhagyta a palyat, jatek vege. Ido: 0");
                return new MainMenuControllerState();
            }
        } else if (s[0].startsWith("test_")){
            try{
                TestControllerState newState = new TestControllerState(s[0].substring(5));
                newState.start();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
            return new LevelSelectControllerState();
        } else if (s[0].equals("back")){
            return new MainMenuControllerState();
        }
        return this;
    }
    @Override
    public void changedTo() {
        System.out.println("LEVEL_MENU");
    }
}
