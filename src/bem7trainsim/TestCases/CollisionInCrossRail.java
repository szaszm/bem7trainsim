package bem7trainsim.TestCases;

import bem7trainsim.CrossRail;
import bem7trainsim.SimpleRail;
import bem7trainsim.Train;

import java.util.ArrayList;

/**
 * Created by Robin.
 */
public class CollisionInCrossRail extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        SimpleRail rail_1 = new SimpleRail();
        SimpleRail rail_2 = new SimpleRail();
        CrossRail cross = new CrossRail();

        cross.addLink(rail_1);
        cross.addLinkToCross(rail_2);
        rail_1.addLink(cross);
        rail_2.addLink(cross);

        Train train_1 = new Train(rail_1, new ArrayList<>());
        Train train_2 = new Train(rail_2, new ArrayList<>());

        train_1.move();
        train_2.move();
    }

    public String getDescription() { return "Utkozes keresztezodesben."; }
}