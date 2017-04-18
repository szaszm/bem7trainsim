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
    public Table LoadTable(String mapFileName) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test/" + mapFileName + ".txt"), "UTF-8"));
        //pálya méretének beolvasása
        LoadSize(br);
        LoadTestText(br);
        //beolvassuk a kezdő pozíciót
        LoadStart(br);
        //beolvassuk a mezőket
        LoadChars(br);
        LoadRails();
        br.readLine();
        LoadSpecials(br);
        LoadTrains(br);
        LoadTestCommands(br);
        LoadExpectedResults(br);
        br.close();
        ConnectRails();

        //létrehozzuk a pályát, ő majd létrehozza az alagútbejáratot és összerak mindent
        return new Table(fields, tunnelEntrances);
    }

    private void LoadExpectedResults(BufferedReader br) throws IOException {
        String line;
        expectedOutput = "";
        while((line = br.readLine()) != null) {
            expectedOutput += line + "\n";
        }
    }

    private void LoadTestCommands(BufferedReader br) throws IOException {
        String line;
        testCommands = new ArrayList<>();
        while((line = br.readLine()) != null && line.length() > 0) {
            testCommands.add(line);
        }
    }

    private void LoadTestText(BufferedReader br) throws IOException {
        String line;
        if((line = br.readLine()) != null){
            testDescription = line;
        } else throw new IOException("Nem sikerült a teszt szövegnek beolvasása.");
        br.readLine();
    }
}
