package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class TunnelEntranceStateUnderConstruction implements TunnelEntranceState {
    @Override
    public boolean nextStraight() {
        return true;
    }

    @Override
    public boolean redLight() {
        return false;
    }
}
