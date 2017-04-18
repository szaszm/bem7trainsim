package bem7trainsim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Csuto on 4/9/2017.
 */
public class Controller {
    public void start() {
    	ControllerState state = new MainMenuControllerState();
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        while (state != null) {
            try {
                state = state.handleCommand(buffer.readLine());
            } catch (IOException e) {
                // Shall never happen
                e.printStackTrace();
            }
        }
    }
}
