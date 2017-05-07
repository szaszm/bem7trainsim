package bem7trainsim;

import java.util.*;

/**
 * Created by marci on 2017.03.17..
 */
public class Tunnel {

    /**
     * Rails of the tunnel.
     */
    private List<Rail> rails;

    /**
     * Entrances of the tunnel
     */
    private List<TunnelEntrance> tunnelEntrances;

    /**
     * Table of the table
     */
    private Table table;

    /**
     * Linked entrances
     */
    private List<Map.Entry<Train, TunnelEntrance>> trainTunnelEntranceMap;


    /**
     * Creates tunnel with the given table and entrances
     * @param table The table on which the tunnel resides
     * @param tunnelEntrances Tunnel entrances
     */
    public Tunnel(Table table, List<TunnelEntrance> tunnelEntrances) {
        rails = new ArrayList<>();
        this.tunnelEntrances = tunnelEntrances;
        trainTunnelEntranceMap = new ArrayList<>();
        this.table = table;
    }

    /**
     * Putting the train into the tunnel through the tunnel entrance
     * @param te The tunnel entrance on which the train entered
     * @param train The train that entered the tunnel
     */
    public void enter(TunnelEntrance te, Train train) {
        trainTunnelEntranceMap.add(new AbstractMap.SimpleEntry<>(train, te));
        te.setState(new TunnelEntranceStateBuiltRed());
        //the train gets into the tunnel, we are setting onSurface to false
        train.onSurface = false;
    }

    /**
     * Returns the other entrance of the tunnel
     * @param te an entrance
     * @return the other entrance
     */
    private TunnelEntrance otherEntrance(TunnelEntrance te) {
        if(tunnelEntrances.indexOf(te) == 1) return tunnelEntrances.get(0);
        return  tunnelEntrances.get(1);
    }

    /**
     * Getting the train out of the tunnel through the entrance
     * @param te The tunnel entrance through which the train leaves the tunnel
     * @param train The train that leaves
     */
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

    /**
     * Checks if the tunnel has a train which entered through the specified entrance
     * @param train The train whose existence is checked
     * @param te The tunnel entrance on which the train entered
     * @return true if the train got into the tunnel through the entrance and is still there
     */
    public boolean hasTrain(Train train, TunnelEntrance te) {
        for (Map.Entry<Train, TunnelEntrance> entry : trainTunnelEntranceMap) {
            if (entry.getKey() == train && entry.getValue() != te) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the entrance's state determined by the other entracnce
     * @param te other entrance
     */
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
