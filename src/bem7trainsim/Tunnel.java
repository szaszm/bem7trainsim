package bem7trainsim;

import java.util.*;

/**
 * Created by marci on 2017.03.17..
 */
public class Tunnel {
    private List<Rail> rails;
    private List<TunnelEntrance> tunnelEntrances;
    private Table table;
    private List<Map.Entry<Train, TunnelEntrance>> trainTunnelEntranceMap;

    private int howManyInside;

    public Tunnel(Table table, List<TunnelEntrance> tunnelEntrances) {
        howManyInside = 0;
        rails = new ArrayList<>();
        this.tunnelEntrances = tunnelEntrances;
        trainTunnelEntranceMap = new ArrayList<>();
        this.table = table;
    }

    public void enter(TunnelEntrance te, Train train) {
        trainTunnelEntranceMap.add(new AbstractMap.SimpleEntry<>(train, te));
        te.setState(new TunnelEntranceStateBuiltRed());
        System.out.println("ALAGUTBA BE: "+Main.identityToString(train)+"  ->  "+Main.identityToString(this));
    }

    private TunnelEntrance otherEntrance(TunnelEntrance te) {
        if(tunnelEntrances.indexOf(te) == 1) return tunnelEntrances.get(0);
        return  tunnelEntrances.get(1);
    }

    public void leave(TunnelEntrance te, Train train) {
        trainTunnelEntranceMap.removeIf(x->x.getKey() == train);
        TunnelEntrance other = otherEntrance(te);
        boolean containsOther = false;
        for (Map.Entry<Train, TunnelEntrance> entry : trainTunnelEntranceMap) {
            if (entry.getValue() == other) {
                containsOther = true;
                break;
            }
        }
        if(!containsOther) {
            te.setState(new TunnelEntranceStateBuilt());
        }
        System.out.println("ALAGUTBOL KI: "+Main.identityToString(train)+"  ->  "+Main.identityToString(this));
    }

    public boolean hasTrain(Train train) {
        for (Map.Entry<Train, TunnelEntrance> entry : trainTunnelEntranceMap) {
            if (entry.getKey() == train) {
                return true;
            }
        }
        return false;
    }

    public void checkEntrance(TunnelEntrance te) {
        if(tunnelEntrances.size() == 0) {
            te.setState(new TunnelEntranceStateUnderConstruction());
        } else {
            te.setState(new TunnelEntranceStateBuilt());
            tunnelEntrances.get(0).setState(new TunnelEntranceStateBuilt());
        }
        tunnelEntrances.add(te);
    }
}
