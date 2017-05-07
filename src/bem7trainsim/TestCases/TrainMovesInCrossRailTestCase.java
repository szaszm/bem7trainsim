package bem7trainsim.TestCases;

import bem7trainsim.CrossRail;
import bem7trainsim.Rail;
import bem7trainsim.SimpleRail;
import bem7trainsim.Train;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin.
 */
public class TrainMovesInCrossRailTestCase extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        List<Rail> rails = new ArrayList<>();
        for(int i = 0; i < 4; ++i) rails.add(new SimpleRail());
        CrossRail crossRail = new CrossRail();

        crossRail.addLink(rails.get(0));
        crossRail.addLink(rails.get(2));
        crossRail.addLinkToCross(rails.get(1));
        crossRail.addLinkToCross(rails.get(3));

        for (Rail rail : rails) rail.addLink(crossRail);
        rails.get(1).addLink(rails.get(2));
        rails.get(2).addLink(rails.get(1));


        Train train = new Train(rails.get(0));
        for(int i = 0; i < 7; ++i) train.move();
    }

    public String getDescription() { return "Haladas keresztezodesben."; }
}