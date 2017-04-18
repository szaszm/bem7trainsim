package bem7trainsim;
/**
 * Created by marci on 2017.03.18.
 * Represents the switch state where the rails are in a straight line
 */
public class SwitchStateStraight implements SwitchState {

    /**
     * Gets the state of the switch
     * @return if the next is straight true, else false.
     */
    @Override
    public boolean nextStraight() {
        return true;
    }
}
