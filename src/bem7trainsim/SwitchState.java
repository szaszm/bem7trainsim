package bem7trainsim;
/**
 * Created by marci on 2017.03.18.
 * Represents the different switching states
 */
public interface SwitchState {
    /**
     * Gets the state of the switch
     * @return if the next is straight true, else false.
     */
    boolean nextStraight();
}
