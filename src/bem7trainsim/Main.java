package bem7trainsim;

//import TestCases.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    /**
     * Starts the application
     * @param args console args
     */
    public static void main(String[] args) {
        Controller ctrl = new Controller();
        ctrl.start();
    }
}
