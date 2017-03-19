package bem7trainsim;

import bem7trainsim.TestCases.BaseTestCase;
import bem7trainsim.TestCases.GetOffTestCase;
import bem7trainsim.TestCases.SimpleCollisionTestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws TrainCollisionException {
        Map<Integer,BaseTestCase> tests = new HashMap<Integer,BaseTestCase>();
        tests.put(1, new SimpleCollisionTestCase());
        tests.put(2, new GetOffTestCase());
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

    public static String identityToString(Object obj) {
        if(obj == null) return "null";
        return obj.getClass().getName()+"@"+Integer.toHexString(System.identityHashCode(obj));
    }

    public static void objectCreated(Object obj) {
        System.out.println(identityToString(obj)+" letrehozva.");
    }
}
