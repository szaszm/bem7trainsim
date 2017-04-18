package bem7trainsim;

import javafx.util.Pair;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.*;
import java.util.Objects;

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
