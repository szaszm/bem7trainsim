package bem7trainsim;
/**
 * Created by marci on 2017.03.18..
 */
public class TunnelEntranceStateBuilt implements TunnelEntranceState {
    /**
     * True if going straight, false if going into the tunnel
     */
    @Override
    public boolean nextStraight() {
        return false;
    }

    /**
     * True if the red light is on
     */
    @Override
    public boolean redLight() {
        return false;
    }

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    @Override
    public String getDrawData() { return "T";}
}
