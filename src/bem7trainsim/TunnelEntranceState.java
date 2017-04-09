package bem7trainsim;
/**
 * Created by marci on 2017.03.17..
 */
public interface TunnelEntranceState {
    boolean nextStraight();
    boolean redLight();
    String getDrawData();
}
