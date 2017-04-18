package bem7trainsim;
/**
 * Created by marci on 2017.03.17..
 */
public interface TunnelEntranceState {

    /**
     * True if going straight, false if going into the tunnel
     */
    boolean nextStraight();

    /**
     * True if the red light is on
     */
    boolean redLight();

    /**
     * Gets the draw data of the field
     * @return the string representing the field
     */
    String getDrawData();
}
