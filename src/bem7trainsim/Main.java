package bem7trainsim;

import bem7trainsim.TestCases.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws CollisionException {
        Map<Integer,BaseTestCase> tests = new HashMap<Integer,BaseTestCase>();
        tests.put(tests.size() + 1, new SimpleCollisionTestCase());
        tests.put(tests.size() + 1, new GetOffTestCase());
        tests.put(tests.size() + 1, new CollisionInCrossRail());
        tests.put(tests.size() + 1, new NotGettingOffTestCase());
        tests.put(tests.size() + 1, new SwitchSwitchesTestCase());
        tests.put(tests.size() + 1, new TrainMovesTestCase());
        tests.put(tests.size() + 1, new TrainMovesInCrossRailTestCase());
        tests.put(tests.size() + 1, new TrainsCollideInTunnelTestCase());
        tests.put(tests.size() + 1, new TrainThroughTunnelTestCase());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean runs = true;
        while (runs) {
            System.out.println("Valasszon a tesztek listajabol vagy zarja be a megfelelo szam begepelesevel");
            System.out.println("0. Kilepes");
            for (Map.Entry<Integer,BaseTestCase> entry : tests.entrySet()) {
                System.out.println( entry.getKey().toString() + ". " + entry.getValue().getDescription());
            }
            try{
                int i = Integer.parseInt(br.readLine());
                if (i == 0) {
                    System.out.println("Kilepes.");
                    runs = false;
                }
                else if (tests.containsKey(i)) {
                    tests.get(i).call();
                }
                else {
                    System.out.println("Nincs ilyen szamu teszteset.");
                }
            } catch(NumberFormatException nfe){
                System.err.println("Nem szam!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
