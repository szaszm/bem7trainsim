package bem7trainsim.TestCases;

import bem7trainsim.Rail;
import bem7trainsim.SimpleRail;
import bem7trainsim.Switch;
import bem7trainsim.Train;

/**
 * Created by Robin.
 */
public class SwitchSwitchesTestCase extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        Rail rail_1 = new SimpleRail();
        Switch sw = new Switch();
        Rail rail_3 = new SimpleRail();
        Rail rail_4 = new SimpleRail();
        rail_1.addLink(sw);
        sw.addLink(rail_1);
        sw.addLink(rail_3);
        sw.addLinkToChange(rail_4);
        rail_3.addLink(sw);
        rail_3.addLink(rail_1);
        rail_4.addLink(sw);

        Train train = new Train(rail_1);
        train.move();
        train.move();
        train.move();
        sw.change();
        train.move();
        train.move();
        train.move();
        train.move();
    }

    public String getDescription() { return "Valto valtasa."; }
}