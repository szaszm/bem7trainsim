package bem7trainsim;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Csuto on 4/18/2017.
 */
public class TestTableLoader extends TableLoader {
    /**
     * The expected end result of the test case
     */
    public String expectedOutput;

    /**
     * The description of the current test case
     */
    public String testDescription;

    /**
     * The commands of test cases when running a test
     */
    public List<String> testCommands;

    /**
     * Loads the table given by its name.
     * @param mapFileName The name of the map in string format. Available names in the documentation.
     * @return the loaded table
     * @throws IOException thrown when the input is not correct
     */
    public Table LoadTable(String mapFileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test/" + mapFileName + ".txt"), "UTF-8"));
        LoadSize(br);
        LoadTestText(br);
        LoadStart(br);
        LoadChars(br);
        LoadRails();
        br.readLine();
        LoadSpecials(br);
        LoadTrains(br);
        LoadTestCommands(br);
        LoadExpectedResults(br);
        br.close();
        ConnectRails();

        //Creating the table with the necessary parameters.
        return new Table(fields, tunnelEntrances);
    }

    /**
     * Loading the expected results with a bufferedreader
     * @param br
     * @throws IOException thrown when the input is not correct
     */
    private void LoadExpectedResults(BufferedReader br) throws IOException {
        String line;
        expectedOutput = "";
        while((line = br.readLine()) != null) {
            expectedOutput += line + "\n";
        }
    }

    /**
     * Loading the test commands with a bufferedreader
     * @param br
     * @throws IOException thrown when the input is not correct
     */
    private void LoadTestCommands(BufferedReader br) throws IOException {
        String line;
        testCommands = new ArrayList<>();
        while((line = br.readLine()) != null && line.length() > 0) {
            testCommands.add(line);
        }
    }

    /**
     * Loading the test text with a bufferedreader
     * @param br
     * @throws IOException thrown when the input is not correct
     */
    private void LoadTestText(BufferedReader br) throws IOException {
        String line;
        if((line = br.readLine()) != null){
            testDescription = line;
        } else throw new IOException("Nem sikerült a teszt szövegnek beolvasása.");
        br.readLine();
    }
}
