package bem7trainsim.TestCases;

import bem7trainsim.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marci on 2017.03.19..
 */
public class TrainThroughTunnelTestCase extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        Field[][] fields = new Field[3][3];
        SimpleRail start = new SimpleRail();
        TunnelEntrance te1 = new TunnelEntrance();
        TunnelEntrance te2 = new TunnelEntrance();
        SimpleRail end = new SimpleRail();
        start.addRail(te1);
        te1.addRail(start);
        te1.addRail(te2);
        te2.addRail(te1);
        te2.addRail(end);
        end.addRail(te2);

        fields[0][0] = start;
        fields[0][1] = te1;
        fields[1][2] = te2;
        fields[2][2] = end;

        Table table = new Table(fields);

        Train train = new Train(start, new ArrayList<>());

        List<TunnelEntrance> tunnelEntrances = new ArrayList<>();
        tunnelEntrances.add(te1);
        tunnelEntrances.add(te2);
        Tunnel tunnel = new Tunnel(table, tunnelEntrances);
        te1.setTunnel(tunnel);
        te2.setTunnel(tunnel);

        train.move();
        train.move();
        train.move();
        train.move();
        train.move();
    }

    public String getDescription() { return "Vonat athalad az alaguton."; }
}
