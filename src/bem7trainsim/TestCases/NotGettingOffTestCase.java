package bem7trainsim.TestCases;

import bem7trainsim.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin.
 */
public class NotGettingOffTestCase extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        List<Rail> rails = new ArrayList<>();
        rails.add(new SimpleRail());
        rails.add(new SimpleRail());
        rails.add(new SimpleRail());
        DownStation downStation = new DownStation(Color.YELLOW);
        rails.add(downStation);
        rails.add(new SimpleRail());
        rails.add(new SimpleRail());

        for(int i = 0; i < rails.size(); ++i) {
            if(i-1 > 0) rails.get(i).addLink(rails.get(i-1));
            if(i+1 < rails.size()) rails.get(i).addLink(rails.get(i+1));
        }

        List<Wagon> wagons = new ArrayList<>();
        wagons.add(new Wagon(Color.GRAY));
        wagons.add(new Wagon(Color.GREEN));
        Train train = new Train(rails.get(0), wagons);

        for(int i = 0; i < 9; ++i) {
            train.move();
        }
    }

    public String getDescription() { return "Nem szukseges leszallas vonatrol."; }
}