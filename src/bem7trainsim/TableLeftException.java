package bem7trainsim;

/**
 * Created by g-bal on 2017. 04. 18.
 * Represents the exception thrown when a train with passengers leaves the table
 */
public class TableLeftException extends Exception {
    public TableLeftException(String msg) {
        super(msg);
    }
}
