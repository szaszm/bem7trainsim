package bem7trainsim.TestCases;

import bem7trainsim.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robin.
 */
public class TrainsCollideInTunnelTestCase extends BaseTestCase {
    @Override
    protected void execute() throws Exception {
        Field[][] fields = new Field[3][3];
        SimpleRail start = new SimpleRail();
        TunnelEntrance te1 = new TunnelEntrance();
        TunnelEntrance te2 = new TunnelEntrance();
        SimpleRail end = new SimpleRail();
        start.addLink(te1);
        te1.addLink(start);
        te1.addLink(te2);
        te2.addLink(te1);
        te2.addLink(end);
        end.addLink(te2);

        fields[0][0] = start;
        fields[0][1] = te1;
        fields[1][2] = te2;
        fields[2][2] = end;

        Table table = new Table(fields);

        Train train1 = new Train(start, new ArrayList<>());
        Train train2 = new Train(end, new ArrayList<>());

        List<TunnelEntrance> tunnelEntrances = new ArrayList<>();
        tunnelEntrances.add(te1);
        tunnelEntrances.add(te2);
        Tunnel tunnel = new Tunnel(table, tunnelEntrances);
        te1.setTunnel(tunnel);
        te2.setTunnel(tunnel);

        train1.move(); train2.move();
        train1.move(); train2.move();
        train1.move(); train2.move();
        train1.move(); train2.move();
    }

    public String getDescription() { return "Vonatok utkoznek az alagutban."; }
}