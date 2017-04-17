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


    public Tunnel(Table table, List<TunnelEntrance> tunnelEntrances) {
        rails = new ArrayList<>();
        this.tunnelEntrances = tunnelEntrances;
        trainTunnelEntranceMap = new ArrayList<>();
        this.table = table;
    }

    public void enter(TunnelEntrance te, Train train) {
        trainTunnelEntranceMap.add(new AbstractMap.SimpleEntry<>(train, te));
        te.setState(new TunnelEntranceStateBuiltRed());
        //a vonat az alagútba lép, ergó nincs a felszínen
        train.onSurface = false;
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
        //ha a vonat elhagyja az alagutat, akkor a felszínre kerül
        train.onSurface = true;
    }

    //hasTrain(Train)-t átneveztem erre, hogy jobban követhető legyen
    public boolean fromInside(Train train, TunnelEntrance te) {
        for (Map.Entry<Train, TunnelEntrance> entry : trainTunnelEntranceMap) {
            if (entry.getKey() == train && entry.getValue() != te) {
                return true;
            }
        }
        return false;
    }

    public void checkEntrance(TunnelEntrance te) throws CannotBuildException {
        //ha még nincs felépítve egy bejárat sem, akkor ez UnderConstruction lesz
        if(tunnelEntrances.size() == 0) {
            te.setState(new TunnelEntranceStateUnderConstruction());
            tunnelEntrances.add(te);
        } else if(tunnelEntrances.size() == 1){
            //ha 1 bejárat van meg, és az, amit állítani akarunk, akkor az vissza megy alap állapotba
            if(tunnelEntrances.get(0) == te){
                te.setState(new TunnelEntranceStateStraight());
                tunnelEntrances.clear();
            } else {
                //ha egy másik, akkor felépül az alagút
                te.setState(new TunnelEntranceStateBuilt());
                tunnelEntrances.get(0).setState(new TunnelEntranceStateBuilt());
                tunnelEntrances.add(te);

                //Elkészítjük az alagútban a sínláncot és hozzákötjük az alagút elejéhez és végéhez.
                int distance = table.howFar(tunnelEntrances.get(0), tunnelEntrances.get(1));
                for(int i = 0; i < distance; i++){
                    rails.add(new SimpleRail(SimpleRail.Orientation.HORIZONTAL));
                    if(i == 0) {
                        tunnelEntrances.get(0).addLinkToTunnel(rails.get(0));
                        rails.get(0).addLink(tunnelEntrances.get(0));
                    } else {
                            rails.get(i-1).addLink(rails.get(i));
                            rails.get(i).addLink(rails.get(i-1));
                    }
                }
                tunnelEntrances.get(1).addLinkToTunnel(rails.get(distance-1));
                rails.get(distance-1).addLink(tunnelEntrances.get(1));
            }
        } else {
            //ha 2 alagútbejárat megvolt már és van az alagútban vonat, akkor nem építhatjük fel a kívánt bejáratot
            if(trainTunnelEntranceMap.size() > 0){
                throw new CannotBuildException("There is at least one train in the tunnel.");
            }
            //ha a felépítendő bejárat a már felépítettek között van, akkor az leépül
            if(tunnelEntrances.contains(te)){
                te.setState(new TunnelEntranceStateStraight());
                tunnelEntrances.remove(te);
                tunnelEntrances.get(0).setState(new TunnelEntranceStateUnderConstruction());
                rails.clear();
            } else {
                //ha ez egy még nem felépített bejárat, akkor a korábban felépített bejáratot lecseréljük erre és újraépítjük az alagút sínjeit
                tunnelEntrances.get(0).setState(new TunnelEntranceStateStraight());
                tunnelEntrances.remove(0);
                te.setState(new TunnelEntranceStateBuilt());
                tunnelEntrances.add(te);
                //újraépítjük az alagút sínjeit
                rails.clear();
                int distance = table.howFar(tunnelEntrances.get(0), tunnelEntrances.get(1));
                for(int i = 0; i < distance; i++){
                    rails.add(new SimpleRail(SimpleRail.Orientation.HORIZONTAL));
                    if(i == 0) {
                        tunnelEntrances.get(0).addLinkToTunnel(rails.get(0));
                        rails.get(0).addLink(tunnelEntrances.get(0));
                    } else {
                        rails.get(i-1).addLink(rails.get(i));
                        rails.get(i).addLink(rails.get(i-1));
                    }
                }
                tunnelEntrances.get(1).addLinkToTunnel(rails.get(distance-1));
                rails.get(distance-1).addLink(tunnelEntrances.get(1));
            }
        }
    }
}
