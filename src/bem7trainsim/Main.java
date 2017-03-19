package bem7trainsim;

import bem7trainsim.TestCases.GetOffTestCase;
import bem7trainsim.TestCases.SimpleCollisionTestCase;

public class Main {

    public static void main(String[] args) throws TrainCollisionException {
        (new SimpleCollisionTestCase()).call();
        (new GetOffTestCase()).call();
    }

    public static String identityToString(Object obj) {
        if(obj == null) return "null";
        return obj.getClass().getName()+"@"+Integer.toHexString(System.identityHashCode(obj));
    }

    public static void objectCreated(Object obj) {
        System.out.println(identityToString(obj)+" created.");
    }
}
