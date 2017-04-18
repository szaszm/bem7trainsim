package bem7trainsim;

/**
 * Created by Csuto on 4/9/2017.
 * Thrown when a tunnel entrance cannot be built
 */
public class CannotBuildException extends Exception {
    public CannotBuildException(String msg) {
        super(msg);
    }
}
