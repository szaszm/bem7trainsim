/**
 * Created by marci on 2017.03.18..
 */
public class TunnelEntranceStateBuiltRed implements TunnelEntranceState {
    @Override
    public boolean nextStraight() {
        return false;
    }

    @Override
    public boolean redLight() {
        return true;
    }
}
