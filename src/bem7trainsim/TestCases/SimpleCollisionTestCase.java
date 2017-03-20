package bem7trainsim.TestCases;

import bem7trainsim.SimpleRail;
import bem7trainsim.Train;

import java.util.ArrayList;

/**
 * Created by marci on 2017.03.19..
 */
public class SimpleCollisionTestCase extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        SimpleRail rail1 = new SimpleRail();
        SimpleRail rail2 = new SimpleRail();
        rail1.addLink(rail2);
        rail2.addLink(rail1);

        Train train1 = new Train(rail1, new ArrayList<>());
        Train train2 = new Train(rail2, new ArrayList<>());

        train2.move();
    }

    public String getDescription() { return "Sima utkozes."; }
}
