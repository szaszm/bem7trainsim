package bem7trainsim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Csuto on 4/9/2017.
 */
public class Controller {
    private Table table;
    private List<Train> trains;
    private boolean run;
    private boolean playstate = false, isGameRun = false, isCreditState = false;
    
    private void moveTrains() throws CollisionException {
        for (Train train :
                trains) {
            train.move();
        }
    }

    private void handleCommand(String command) {
        //TODO: Kezel egy parancsot, és futtatja a hozzá tartozó műveleteket.
        String[] s = command.split(" ");
        if (playstate) {
            switch (s[0]) {
                //TODO: cases
                case "back":
                    playstate = false;
                    break;
            }
        }
        else if (isCreditState) {
            switch (s[0]) {
                case "back":
                    isCreditState = false;
                break;
            }
        }
        else {
            switch (s[0]) {
                case "play":
                    playstate = true;
                    break;
                case "credits":
                    System.out.println("Csutorás Robin\n"); //TODO: nevek
                    isCreditState = true;
                    break;
                case "exit":
                    run = false;
                    break;
            }
        }
    }

    private void loadMap(String mapFileName) {
        //TODO: Betölt egy pályát a megadott fájlból, és beállítja azt a jelenlegi pályának.
    }
    public void start() {
        // TODO: Elindítja az események kezelését. Ha teszt van betöltve, akkor futtatja a tesztet, majd a végén kiírja a végső állapotot.
        run = true;
        while (run) {
            BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
            try {
                handleCommand(buffer.readLine());
            } catch (IOException e) {
                // TODO: do something
                e.printStackTrace();
            }
        }
    }
}
