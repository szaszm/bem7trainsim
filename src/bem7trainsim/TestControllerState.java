package bem7trainsim;

import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * Controller state which runs tests
 * Created by Csuto on 4/18/2017.
 */
public class TestControllerState extends PlayControllerState implements ActionListener {
    /**
     * The expected end result of the test case
     */
    private String expectedOutput;

    /**
     * The description of the current test case
     */
    protected String testDescription;

    /**
     * The commands of test cases when running a test
     */
    protected List<String> testCommands;

    /**
     * A printstream for the outputs
     */
    PrintStream out;

    /**
     * @param map The name of the map in string format. Available names in the documentation.
     * @throws IOException thrown when the input is not correct
     */
    public TestControllerState(Controller c, String map) throws IOException  {
        super(c, map); // loads the map
    }

    /**
     * Runs the test
     */
    @Override
    public void start() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        out = new PrintStream(baos);

        try {
            moveTrains();
            for(String cmd: testCommands) {
                handleCommand(cmd);
            }
        } catch (CollisionException e) {
            out.print("CollisionException\n");
        } catch (TableLeftException e) {
            out.print("TableLeftException\n");
        }
        String content = "";
        FieldDrawData[][] fdd = table.getDrawData();
        for (int y = 0; y < fdd.length; y++) {
            FieldDrawData[] row = fdd[y];
            for (int x = 0; x < row.length; x++) {
                FieldDrawData field = row[x];
                content += field.getLayer(field.getNumberOfLayers() - 1).substring(0, 1);
            }
            content += "\n";
        }
        content += new String(baos.toByteArray(), StandardCharsets.UTF_8);
        out = System.out;
        boolean success = Objects.equals(content, expectedOutput);
        if(success) {
            out.print("[OK]   ");
        } else {
            out.print("[FAIL] ");
        }
        out.println(testDescription);

        if(!success) {
            out.println("expected:");
            out.println(expectedOutput);
            out.println("got:");
            out.println(content);
        }
    }

    /**
     * Handles the commands but not throwing exceptions.
     * @param command The command in string format. Available commands list is in the documentation.
     * @return The controller state which the controller steps into
     */
    @Override
    public ControllerState handleCommand(String command) {
        try{
            return handleCommandWithoutException(command);
        } catch(CannotSwitchException e){
            out.print("CannotSwitchException\n");
        }
        catch(CannotBuildException e){
            out.print("CannotBuildException\n");
        }
        MainMenuControllerState state = new MainMenuControllerState(controller);
        controller.setState(state);
        return state; // exit if exception happened
    }

    /**
     * Tries moving the trains and increments time.
     * @return The controller state which the controller steps into
     */
    @Override
    protected ControllerState tick() {
        currentTime++;
        try {
            moveTrains();
        } catch (CollisionException e) {
            out.print("CollisionException\n");
            MainMenuControllerState state = new MainMenuControllerState(controller);
            controller.setState(state);
            return state;
        } catch (TableLeftException e){
            out.print("TableLeftException\n");
            MainMenuControllerState state = new MainMenuControllerState(controller);
            controller.setState(state);
            return state;
        }
        if(isWin()) {
            MainMenuControllerState state = new MainMenuControllerState(controller);
            controller.setState(state);
            return state;
        }
        return this;
    }

    /**
     * Loads a map from file name
     * @param mapFileName the name of the map in string format. Available names in the documentation.
     * @throws IOException thrown when the input is not correct.
     */
    @Override
    protected void loadMap(String mapFileName) throws IOException {
        TestTableLoader tl = new TestTableLoader();
        table = tl.LoadTable(mapFileName);
        trains = tl.trains;
        upstations = tl.upstations;
        startRail = tl.startRail;
        trainData = tl.trainData;
        testCommands = tl.testCommands;
        testDescription = tl.testDescription;
        expectedOutput = tl.expectedOutput;
    }
}
