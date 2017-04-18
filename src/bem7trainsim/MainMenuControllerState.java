package bem7trainsim;

/**
 * Created by Csuto on 4/18/2017.
 */
public class MainMenuControllerState extends ControllerState {
    public MainMenuControllerState() {
        System.out.println("MAIN_MENU");
    }
    @Override
    public ControllerState handleCommand(String command) {
        String[] s = command.split(" ");
        switch (s[0]) {
            case "play":
                return new LevelSelectControllerState();
            case "credits":
                System.out.println("Csutorás Robin\nGnandt Balázs\nSzász Márton\nTamás Csongor\nZabó Kristóf");
                break;
            case "exit":
                return null;
        }
        return this;
    }
}
