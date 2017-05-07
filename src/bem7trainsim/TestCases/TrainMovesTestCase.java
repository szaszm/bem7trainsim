package bem7trainsim.TestCases;

import bem7trainsim.Rail;
import bem7trainsim.SimpleRail;
import bem7trainsim.Train;
import bem7trainsim.Wagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin.
 */
public class TrainMovesTestCase extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        List<Rail> rails = new ArrayList<>();
        for(int i = 0; i < 4; ++i) rails.add(new SimpleRail());
        for(int i = 0; i < 4; ++i) {
            if(i-1 > 0) rails.get(i).addLink(rails.get(i-1));
            if(i+1 < rails.size()) rails.get(i).addLink(rails.get(i+1));
        }

        List<Wagon> wagons = new ArrayList<>();
        wagons.add(new Wagon(Color.BLUE));
        wagons.add(new Wagon(Color.RED));

        Train train = new Train(rails.get(0), wagons);
        for(int i = 0; i < 5; ++i) train.move();
    }

    public String getDescription() { return "Vonat haladas."; }
}