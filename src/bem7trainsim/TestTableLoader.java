package bem7trainsim;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Csuto on 4/18/2017.
 */
public class TestTableLoader extends TableLoader {
    public Table LoadTable(String mapFileName) throws IOException {
        //TODO: make test use this. some more reading needed to be added.
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test/" + mapFileName + ".txt"), "UTF-8"));
        //pálya méretének beolvasása
        LoadSize(br);
        //beolvassuk a kezdő pozíciót
        LoadStart(br);
        //beolvassuk a mezőket
        LoadChars(br);
        LoadRails();
        br.readLine();
        LoadSpecials(br);
        LoadTrains(br);
        br.close();
        ConnectRails();

        //létrehozzuk a pályát, ő majd létrehozza az alagútbejáratot és összerak mindent
        return new Table(fields, tunnelEntrances);
    }
}
